package org.spongycastle.asn1.x509;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;












public class DigestInfo
  extends ASN1Object
{
  private byte[] digest;
  private AlgorithmIdentifier algId;
  
  public static DigestInfo getInstance(ASN1TaggedObject obj, boolean explicit)
  {
    return getInstance(ASN1Sequence.getInstance(obj, explicit));
  }
  

  public static DigestInfo getInstance(Object obj)
  {
    if ((obj instanceof DigestInfo))
    {
      return (DigestInfo)obj;
    }
    if (obj != null)
    {
      return new DigestInfo(ASN1Sequence.getInstance(obj));
    }
    
    return null;
  }
  


  public DigestInfo(AlgorithmIdentifier algId, byte[] digest)
  {
    this.digest = digest;
    this.algId = algId;
  }
  

  public DigestInfo(ASN1Sequence obj)
  {
    Enumeration e = obj.getObjects();
    
    algId = AlgorithmIdentifier.getInstance(e.nextElement());
    digest = ASN1OctetString.getInstance(e.nextElement()).getOctets();
  }
  
  public AlgorithmIdentifier getAlgorithmId()
  {
    return algId;
  }
  
  public byte[] getDigest()
  {
    return digest;
  }
  
  public ASN1Primitive toASN1Primitive()
  {
    ASN1EncodableVector v = new ASN1EncodableVector();
    
    v.add(algId);
    v.add(new DEROctetString(digest));
    
    return new DERSequence(v);
  }
}
