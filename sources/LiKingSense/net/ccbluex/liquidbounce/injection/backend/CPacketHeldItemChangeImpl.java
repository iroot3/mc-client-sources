/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  org.jetbrains.annotations.NotNull
 */
package net.ccbluex.liquidbounce.injection.backend;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.ccbluex.liquidbounce.api.minecraft.network.play.client.ICPacketHeldItemChange;
import net.ccbluex.liquidbounce.injection.backend.PacketImpl;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00020\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2={"Lnet/ccbluex/liquidbounce/injection/backend/CPacketHeldItemChangeImpl;", "T", "Lnet/minecraft/network/play/client/CPacketHeldItemChange;", "Lnet/ccbluex/liquidbounce/injection/backend/PacketImpl;", "Lnet/ccbluex/liquidbounce/api/minecraft/network/play/client/ICPacketHeldItemChange;", "wrapped", "(Lnet/minecraft/network/play/client/CPacketHeldItemChange;)V", "slotId", "", "getSlotId", "()I", "LiKingSense"})
public final class CPacketHeldItemChangeImpl<T extends CPacketHeldItemChange>
extends PacketImpl<T>
implements ICPacketHeldItemChange {
    @Override
    public int getSlotId() {
        return ((CPacketHeldItemChange)this.getWrapped()).func_149614_c();
    }

    public CPacketHeldItemChangeImpl(@NotNull T wrapped) {
        Intrinsics.checkParameterIsNotNull(wrapped, (String)"wrapped");
        super((Packet)wrapped);
    }
}

