/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.reflect.TypeCapture;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import javax.annotation.Nullable;

@Beta
public abstract class TypeParameter<T>
extends TypeCapture<T> {
    final TypeVariable<?> typeVariable;

    protected TypeParameter() {
        Type type = this.capture();
        Preconditions.checkArgument(type instanceof TypeVariable, "%s should be a type variable.", (Object)type);
        this.typeVariable = (TypeVariable)type;
    }

    public final int hashCode() {
        return this.typeVariable.hashCode();
    }

    public final boolean equals(@Nullable Object object) {
        if (object instanceof TypeParameter) {
            TypeParameter typeParameter = (TypeParameter)object;
            return this.typeVariable.equals(typeParameter.typeVariable);
        }
        return true;
    }

    public String toString() {
        return this.typeVariable.toString();
    }
}

