package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.macs.CMac;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;




























public class EAXBlockCipher
  implements AEADBlockCipher
{
  private static final byte nTAG = 0;
  private static final byte hTAG = 1;
  private static final byte cTAG = 2;
  private SICBlockCipher cipher;
  private boolean forEncryption;
  private int blockSize;
  private Mac mac;
  private byte[] nonceMac;
  private byte[] associatedTextMac;
  private byte[] macBlock;
  private int macSize;
  private byte[] bufBlock;
  private int bufOff;
  private boolean cipherInitialized;
  private byte[] initialAssociatedText;
  
  public EAXBlockCipher(BlockCipher cipher)
  {
    blockSize = cipher.getBlockSize();
    mac = new CMac(cipher);
    macBlock = new byte[blockSize];
    associatedTextMac = new byte[mac.getMacSize()];
    nonceMac = new byte[mac.getMacSize()];
    this.cipher = new SICBlockCipher(cipher);
  }
  
  public String getAlgorithmName()
  {
    return cipher.getUnderlyingCipher().getAlgorithmName() + "/EAX";
  }
  
  public BlockCipher getUnderlyingCipher()
  {
    return cipher.getUnderlyingCipher();
  }
  
  public int getBlockSize()
  {
    return cipher.getBlockSize();
  }
  
  public void init(boolean forEncryption, CipherParameters params)
    throws IllegalArgumentException
  {
    this.forEncryption = forEncryption;
    

    CipherParameters keyParam;
    
    if ((params instanceof AEADParameters))
    {
      AEADParameters param = (AEADParameters)params;
      
      byte[] nonce = param.getNonce();
      initialAssociatedText = param.getAssociatedText();
      macSize = (param.getMacSize() / 8);
      keyParam = param.getKey();
    } else { CipherParameters keyParam;
      if ((params instanceof ParametersWithIV))
      {
        ParametersWithIV param = (ParametersWithIV)params;
        
        byte[] nonce = param.getIV();
        initialAssociatedText = null;
        macSize = (mac.getMacSize() / 2);
        keyParam = param.getParameters();
      }
      else
      {
        throw new IllegalArgumentException("invalid parameters passed to EAX"); } }
    CipherParameters keyParam;
    byte[] nonce;
    bufBlock = new byte[forEncryption ? blockSize : blockSize + macSize];
    
    byte[] tag = new byte[blockSize];
    

    mac.init(keyParam);
    
    tag[(blockSize - 1)] = 0;
    mac.update(tag, 0, blockSize);
    mac.update(nonce, 0, nonce.length);
    mac.doFinal(nonceMac, 0);
    

    cipher.init(true, new ParametersWithIV(null, nonceMac));
    
    reset();
  }
  
  private void initCipher()
  {
    if (cipherInitialized)
    {
      return;
    }
    
    cipherInitialized = true;
    
    mac.doFinal(associatedTextMac, 0);
    
    byte[] tag = new byte[blockSize];
    tag[(blockSize - 1)] = 2;
    mac.update(tag, 0, blockSize);
  }
  
  private void calculateMac()
  {
    byte[] outC = new byte[blockSize];
    mac.doFinal(outC, 0);
    
    for (int i = 0; i < macBlock.length; i++)
    {
      macBlock[i] = ((byte)(nonceMac[i] ^ associatedTextMac[i] ^ outC[i]));
    }
  }
  
  public void reset()
  {
    reset(true);
  }
  

  private void reset(boolean clearMac)
  {
    cipher.reset();
    mac.reset();
    
    bufOff = 0;
    Arrays.fill(bufBlock, (byte)0);
    
    if (clearMac)
    {
      Arrays.fill(macBlock, (byte)0);
    }
    
    byte[] tag = new byte[blockSize];
    tag[(blockSize - 1)] = 1;
    mac.update(tag, 0, blockSize);
    
    cipherInitialized = false;
    
    if (initialAssociatedText != null)
    {
      processAADBytes(initialAssociatedText, 0, initialAssociatedText.length);
    }
  }
  
  public void processAADByte(byte in)
  {
    if (cipherInitialized)
    {
      throw new IllegalStateException("AAD data cannot be added after encryption/decryption processing has begun.");
    }
    mac.update(in);
  }
  
  public void processAADBytes(byte[] in, int inOff, int len)
  {
    if (cipherInitialized)
    {
      throw new IllegalStateException("AAD data cannot be added after encryption/decryption processing has begun.");
    }
    mac.update(in, inOff, len);
  }
  
  public int processByte(byte in, byte[] out, int outOff)
    throws DataLengthException
  {
    initCipher();
    
    return process(in, out, outOff);
  }
  
  public int processBytes(byte[] in, int inOff, int len, byte[] out, int outOff)
    throws DataLengthException
  {
    initCipher();
    
    if (in.length < inOff + len)
    {
      throw new DataLengthException("Input buffer too short");
    }
    
    int resultLen = 0;
    
    for (int i = 0; i != len; i++)
    {
      resultLen += process(in[(inOff + i)], out, outOff + resultLen);
    }
    
    return resultLen;
  }
  
  public int doFinal(byte[] out, int outOff)
    throws IllegalStateException, InvalidCipherTextException
  {
    initCipher();
    
    int extra = bufOff;
    byte[] tmp = new byte[bufBlock.length];
    
    bufOff = 0;
    
    if (forEncryption)
    {
      if (out.length < outOff + extra + macSize)
      {
        throw new OutputLengthException("Output buffer too short");
      }
      cipher.processBlock(bufBlock, 0, tmp, 0);
      
      System.arraycopy(tmp, 0, out, outOff, extra);
      
      mac.update(tmp, 0, extra);
      
      calculateMac();
      
      System.arraycopy(macBlock, 0, out, outOff + extra, macSize);
      
      reset(false);
      
      return extra + macSize;
    }
    

    if (extra < macSize)
    {
      throw new InvalidCipherTextException("data too short");
    }
    if (out.length < outOff + extra - macSize)
    {
      throw new OutputLengthException("Output buffer too short");
    }
    if (extra > macSize)
    {
      mac.update(bufBlock, 0, extra - macSize);
      
      cipher.processBlock(bufBlock, 0, tmp, 0);
      
      System.arraycopy(tmp, 0, out, outOff, extra - macSize);
    }
    
    calculateMac();
    
    if (!verifyMac(bufBlock, extra - macSize))
    {
      throw new InvalidCipherTextException("mac check in EAX failed");
    }
    
    reset(false);
    
    return extra - macSize;
  }
  

  public byte[] getMac()
  {
    byte[] mac = new byte[macSize];
    
    System.arraycopy(macBlock, 0, mac, 0, macSize);
    
    return mac;
  }
  
  public int getUpdateOutputSize(int len)
  {
    int totalData = len + bufOff;
    if (!forEncryption)
    {
      if (totalData < macSize)
      {
        return 0;
      }
      totalData -= macSize;
    }
    return totalData - totalData % blockSize;
  }
  
  public int getOutputSize(int len)
  {
    int totalData = len + bufOff;
    
    if (forEncryption)
    {
      return totalData + macSize;
    }
    
    return totalData < macSize ? 0 : totalData - macSize;
  }
  
  private int process(byte b, byte[] out, int outOff)
  {
    bufBlock[(bufOff++)] = b;
    
    if (bufOff == bufBlock.length)
    {
      if (out.length < outOff + blockSize)
      {
        throw new OutputLengthException("Output buffer is too short");
      }
      

      int size;
      

      if (forEncryption)
      {
        int size = cipher.processBlock(bufBlock, 0, out, outOff);
        
        mac.update(out, outOff, blockSize);
      }
      else
      {
        mac.update(bufBlock, 0, blockSize);
        
        size = cipher.processBlock(bufBlock, 0, out, outOff);
      }
      
      bufOff = 0;
      if (!forEncryption)
      {
        System.arraycopy(bufBlock, blockSize, bufBlock, 0, macSize);
        bufOff = macSize;
      }
      
      return size;
    }
    
    return 0;
  }
  
  private boolean verifyMac(byte[] mac, int off)
  {
    int nonEqual = 0;
    
    for (int i = 0; i < macSize; i++)
    {
      nonEqual |= macBlock[i] ^ mac[(off + i)];
    }
    
    return nonEqual == 0;
  }
}
