package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERTaggedObject;

















public class SignerIdentifier
  extends ASN1Object
  implements ASN1Choice
{
  private ASN1Encodable id;
  
  public SignerIdentifier(IssuerAndSerialNumber id)
  {
    this.id = id;
  }
  

  public SignerIdentifier(ASN1OctetString id)
  {
    this.id = new DERTaggedObject(false, 0, id);
  }
  

  public SignerIdentifier(ASN1Primitive id)
  {
    this.id = id;
  }
  
















  public static SignerIdentifier getInstance(Object o)
  {
    if ((o == null) || ((o instanceof SignerIdentifier)))
    {
      return (SignerIdentifier)o;
    }
    
    if ((o instanceof IssuerAndSerialNumber))
    {
      return new SignerIdentifier((IssuerAndSerialNumber)o);
    }
    
    if ((o instanceof ASN1OctetString))
    {
      return new SignerIdentifier((ASN1OctetString)o);
    }
    
    if ((o instanceof ASN1Primitive))
    {
      return new SignerIdentifier((ASN1Primitive)o);
    }
    

    throw new IllegalArgumentException("Illegal object in SignerIdentifier: " + o.getClass().getName());
  }
  
  public boolean isTagged()
  {
    return id instanceof ASN1TaggedObject;
  }
  
  public ASN1Encodable getId()
  {
    if ((id instanceof ASN1TaggedObject))
    {
      return ASN1OctetString.getInstance((ASN1TaggedObject)id, false);
    }
    
    return id;
  }
  



  public ASN1Primitive toASN1Primitive()
  {
    return id.toASN1Primitive();
  }
}
