/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelException;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.handler.codec.http.multipart.AbstractMemoryHttpData;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpData;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.util.ReferenceCounted;
import java.io.IOException;
import java.nio.charset.Charset;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class MemoryAttribute
extends AbstractMemoryHttpData
implements Attribute {
    public MemoryAttribute(String string) {
        this(string, HttpConstants.DEFAULT_CHARSET);
    }

    public MemoryAttribute(String string, long l) {
        this(string, l, HttpConstants.DEFAULT_CHARSET);
    }

    public MemoryAttribute(String string, Charset charset) {
        super(string, charset, 0L);
    }

    public MemoryAttribute(String string, long l, Charset charset) {
        super(string, charset, l);
    }

    public MemoryAttribute(String string, String string2) throws IOException {
        this(string, string2, HttpConstants.DEFAULT_CHARSET);
    }

    public MemoryAttribute(String string, String string2, Charset charset) throws IOException {
        super(string, charset, 0L);
        this.setValue(string2);
    }

    @Override
    public InterfaceHttpData.HttpDataType getHttpDataType() {
        return InterfaceHttpData.HttpDataType.Attribute;
    }

    @Override
    public String getValue() {
        return this.getByteBuf().toString(this.getCharset());
    }

    @Override
    public void setValue(String string) throws IOException {
        if (string == null) {
            throw new NullPointerException("value");
        }
        byte[] byArray = string.getBytes(this.getCharset());
        this.checkSize(byArray.length);
        ByteBuf byteBuf = Unpooled.wrappedBuffer(byArray);
        if (this.definedSize > 0L) {
            this.definedSize = byteBuf.readableBytes();
        }
        this.setContent(byteBuf);
    }

    @Override
    public void addContent(ByteBuf byteBuf, boolean bl) throws IOException {
        int n = byteBuf.readableBytes();
        this.checkSize(this.size + (long)n);
        if (this.definedSize > 0L && this.definedSize < this.size + (long)n) {
            this.definedSize = this.size + (long)n;
        }
        super.addContent(byteBuf, bl);
    }

    public int hashCode() {
        return this.getName().hashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof Attribute)) {
            return true;
        }
        Attribute attribute = (Attribute)object;
        return this.getName().equalsIgnoreCase(attribute.getName());
    }

    @Override
    public int compareTo(InterfaceHttpData interfaceHttpData) {
        if (!(interfaceHttpData instanceof Attribute)) {
            throw new ClassCastException("Cannot compare " + (Object)((Object)this.getHttpDataType()) + " with " + (Object)((Object)interfaceHttpData.getHttpDataType()));
        }
        return this.compareTo((Attribute)interfaceHttpData);
    }

    @Override
    public int compareTo(Attribute attribute) {
        return this.getName().compareToIgnoreCase(attribute.getName());
    }

    public String toString() {
        return this.getName() + '=' + this.getValue();
    }

    @Override
    public Attribute copy() {
        ByteBuf byteBuf = this.content();
        return this.replace(byteBuf != null ? byteBuf.copy() : null);
    }

    @Override
    public Attribute duplicate() {
        ByteBuf byteBuf = this.content();
        return this.replace(byteBuf != null ? byteBuf.duplicate() : null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Attribute retainedDuplicate() {
        ByteBuf byteBuf = this.content();
        if (byteBuf != null) {
            byteBuf = byteBuf.retainedDuplicate();
            boolean bl = false;
            try {
                Attribute attribute = this.replace(byteBuf);
                bl = true;
                Attribute attribute2 = attribute;
                return attribute2;
            } finally {
                if (!bl) {
                    byteBuf.release();
                }
            }
        }
        return this.replace(null);
    }

    @Override
    public Attribute replace(ByteBuf byteBuf) {
        MemoryAttribute memoryAttribute = new MemoryAttribute(this.getName());
        memoryAttribute.setCharset(this.getCharset());
        if (byteBuf != null) {
            try {
                memoryAttribute.setContent(byteBuf);
            } catch (IOException iOException) {
                throw new ChannelException(iOException);
            }
        }
        return memoryAttribute;
    }

    @Override
    public Attribute retain() {
        super.retain();
        return this;
    }

    @Override
    public Attribute retain(int n) {
        super.retain(n);
        return this;
    }

    @Override
    public Attribute touch() {
        super.touch();
        return this;
    }

    @Override
    public Attribute touch(Object object) {
        super.touch(object);
        return this;
    }

    @Override
    public HttpData touch(Object object) {
        return this.touch(object);
    }

    @Override
    public HttpData touch() {
        return this.touch();
    }

    @Override
    public HttpData retain(int n) {
        return this.retain(n);
    }

    @Override
    public HttpData retain() {
        return this.retain();
    }

    @Override
    public HttpData replace(ByteBuf byteBuf) {
        return this.replace(byteBuf);
    }

    @Override
    public HttpData retainedDuplicate() {
        return this.retainedDuplicate();
    }

    @Override
    public HttpData duplicate() {
        return this.duplicate();
    }

    @Override
    public HttpData copy() {
        return this.copy();
    }

    @Override
    public InterfaceHttpData touch(Object object) {
        return this.touch(object);
    }

    @Override
    public InterfaceHttpData touch() {
        return this.touch();
    }

    @Override
    public InterfaceHttpData retain(int n) {
        return this.retain(n);
    }

    @Override
    public InterfaceHttpData retain() {
        return this.retain();
    }

    @Override
    public int compareTo(Object object) {
        return this.compareTo((InterfaceHttpData)object);
    }

    @Override
    public ReferenceCounted touch(Object object) {
        return this.touch(object);
    }

    @Override
    public ReferenceCounted touch() {
        return this.touch();
    }

    @Override
    public ReferenceCounted retain(int n) {
        return this.retain(n);
    }

    @Override
    public ReferenceCounted retain() {
        return this.retain();
    }

    @Override
    public ByteBufHolder touch(Object object) {
        return this.touch(object);
    }

    @Override
    public ByteBufHolder touch() {
        return this.touch();
    }

    @Override
    public ByteBufHolder retain(int n) {
        return this.retain(n);
    }

    @Override
    public ByteBufHolder retain() {
        return this.retain();
    }

    @Override
    public ByteBufHolder replace(ByteBuf byteBuf) {
        return this.replace(byteBuf);
    }

    @Override
    public ByteBufHolder retainedDuplicate() {
        return this.retainedDuplicate();
    }

    @Override
    public ByteBufHolder duplicate() {
        return this.duplicate();
    }

    @Override
    public ByteBufHolder copy() {
        return this.copy();
    }
}

