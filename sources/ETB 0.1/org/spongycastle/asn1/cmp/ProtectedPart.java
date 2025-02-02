package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class ProtectedPart
  extends ASN1Object
{
  private PKIHeader header;
  private PKIBody body;
  
  private ProtectedPart(ASN1Sequence seq)
  {
    header = PKIHeader.getInstance(seq.getObjectAt(0));
    body = PKIBody.getInstance(seq.getObjectAt(1));
  }
  
  public static ProtectedPart getInstance(Object o)
  {
    if ((o instanceof ProtectedPart))
    {
      return (ProtectedPart)o;
    }
    
    if (o != null)
    {
      return new ProtectedPart(ASN1Sequence.getInstance(o));
    }
    
    return null;
  }
  
  public ProtectedPart(PKIHeader header, PKIBody body)
  {
    this.header = header;
    this.body = body;
  }
  
  public PKIHeader getHeader()
  {
    return header;
  }
  
  public PKIBody getBody()
  {
    return body;
  }
  









  public ASN1Primitive toASN1Primitive()
  {
    ASN1EncodableVector v = new ASN1EncodableVector();
    
    v.add(header);
    v.add(body);
    
    return new DERSequence(v);
  }
}
