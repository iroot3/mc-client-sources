/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.http;

import org.apache.http.HttpException;

public class ProtocolException
extends HttpException {
    private static final long serialVersionUID = -2143571074341228994L;

    public ProtocolException() {
    }

    public ProtocolException(String string) {
        super(string);
    }

    public ProtocolException(String string, Throwable throwable) {
        super(string, throwable);
    }
}

