/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.ForwardingSortedMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.function.BiFunction;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
@GwtIncompatible
public abstract class ForwardingNavigableMap<K, V>
extends ForwardingSortedMap<K, V>
implements NavigableMap<K, V> {
    protected ForwardingNavigableMap() {
    }

    @Override
    protected abstract NavigableMap<K, V> delegate();

    @Override
    public Map.Entry<K, V> lowerEntry(K k) {
        return this.delegate().lowerEntry(k);
    }

    protected Map.Entry<K, V> standardLowerEntry(K k) {
        return this.headMap(k, true).lastEntry();
    }

    @Override
    public K lowerKey(K k) {
        return this.delegate().lowerKey(k);
    }

    protected K standardLowerKey(K k) {
        return Maps.keyOrNull(this.lowerEntry(k));
    }

    @Override
    public Map.Entry<K, V> floorEntry(K k) {
        return this.delegate().floorEntry(k);
    }

    protected Map.Entry<K, V> standardFloorEntry(K k) {
        return this.headMap(k, false).lastEntry();
    }

    @Override
    public K floorKey(K k) {
        return this.delegate().floorKey(k);
    }

    protected K standardFloorKey(K k) {
        return Maps.keyOrNull(this.floorEntry(k));
    }

    @Override
    public Map.Entry<K, V> ceilingEntry(K k) {
        return this.delegate().ceilingEntry(k);
    }

    protected Map.Entry<K, V> standardCeilingEntry(K k) {
        return this.tailMap(k, false).firstEntry();
    }

    @Override
    public K ceilingKey(K k) {
        return this.delegate().ceilingKey(k);
    }

    protected K standardCeilingKey(K k) {
        return Maps.keyOrNull(this.ceilingEntry(k));
    }

    @Override
    public Map.Entry<K, V> higherEntry(K k) {
        return this.delegate().higherEntry(k);
    }

    protected Map.Entry<K, V> standardHigherEntry(K k) {
        return this.tailMap(k, true).firstEntry();
    }

    @Override
    public K higherKey(K k) {
        return this.delegate().higherKey(k);
    }

    protected K standardHigherKey(K k) {
        return Maps.keyOrNull(this.higherEntry(k));
    }

    @Override
    public Map.Entry<K, V> firstEntry() {
        return this.delegate().firstEntry();
    }

    protected Map.Entry<K, V> standardFirstEntry() {
        return Iterables.getFirst(this.entrySet(), null);
    }

    protected K standardFirstKey() {
        Map.Entry<K, V> entry = this.firstEntry();
        if (entry == null) {
            throw new NoSuchElementException();
        }
        return entry.getKey();
    }

    @Override
    public Map.Entry<K, V> lastEntry() {
        return this.delegate().lastEntry();
    }

    protected Map.Entry<K, V> standardLastEntry() {
        return Iterables.getFirst(this.descendingMap().entrySet(), null);
    }

    protected K standardLastKey() {
        Map.Entry<K, V> entry = this.lastEntry();
        if (entry == null) {
            throw new NoSuchElementException();
        }
        return entry.getKey();
    }

    @Override
    public Map.Entry<K, V> pollFirstEntry() {
        return this.delegate().pollFirstEntry();
    }

    protected Map.Entry<K, V> standardPollFirstEntry() {
        return Iterators.pollNext(this.entrySet().iterator());
    }

    @Override
    public Map.Entry<K, V> pollLastEntry() {
        return this.delegate().pollLastEntry();
    }

    protected Map.Entry<K, V> standardPollLastEntry() {
        return Iterators.pollNext(this.descendingMap().entrySet().iterator());
    }

    @Override
    public NavigableMap<K, V> descendingMap() {
        return this.delegate().descendingMap();
    }

    @Override
    public NavigableSet<K> navigableKeySet() {
        return this.delegate().navigableKeySet();
    }

    @Override
    public NavigableSet<K> descendingKeySet() {
        return this.delegate().descendingKeySet();
    }

    @Beta
    protected NavigableSet<K> standardDescendingKeySet() {
        return this.descendingMap().navigableKeySet();
    }

    @Override
    protected SortedMap<K, V> standardSubMap(K k, K k2) {
        return this.subMap(k, true, k2, true);
    }

    @Override
    public NavigableMap<K, V> subMap(K k, boolean bl, K k2, boolean bl2) {
        return this.delegate().subMap(k, bl, k2, bl2);
    }

    @Override
    public NavigableMap<K, V> headMap(K k, boolean bl) {
        return this.delegate().headMap(k, bl);
    }

    @Override
    public NavigableMap<K, V> tailMap(K k, boolean bl) {
        return this.delegate().tailMap(k, bl);
    }

    protected SortedMap<K, V> standardHeadMap(K k) {
        return this.headMap(k, true);
    }

    protected SortedMap<K, V> standardTailMap(K k) {
        return this.tailMap(k, false);
    }

    @Override
    protected SortedMap delegate() {
        return this.delegate();
    }

    @Override
    protected Map delegate() {
        return this.delegate();
    }

    @Override
    protected Object delegate() {
        return this.delegate();
    }

    @Beta
    protected class StandardNavigableKeySet
    extends Maps.NavigableKeySet<K, V> {
        final ForwardingNavigableMap this$0;

        public StandardNavigableKeySet(ForwardingNavigableMap forwardingNavigableMap) {
            this.this$0 = forwardingNavigableMap;
            super(forwardingNavigableMap);
        }
    }

    @Beta
    protected class StandardDescendingMap
    extends Maps.DescendingMap<K, V> {
        final ForwardingNavigableMap this$0;

        public StandardDescendingMap(ForwardingNavigableMap forwardingNavigableMap) {
            this.this$0 = forwardingNavigableMap;
        }

        @Override
        NavigableMap<K, V> forward() {
            return this.this$0;
        }

        @Override
        public void replaceAll(BiFunction<? super K, ? super V, ? extends V> biFunction) {
            this.forward().replaceAll(biFunction);
        }

        @Override
        protected Iterator<Map.Entry<K, V>> entryIterator() {
            return new Iterator<Map.Entry<K, V>>(this){
                private Map.Entry<K, V> toRemove;
                private Map.Entry<K, V> nextOrNull;
                final StandardDescendingMap this$1;
                {
                    this.this$1 = standardDescendingMap;
                    this.toRemove = null;
                    this.nextOrNull = this.this$1.forward().lastEntry();
                }

                @Override
                public boolean hasNext() {
                    return this.nextOrNull != null;
                }

                @Override
                public Map.Entry<K, V> next() {
                    if (!this.hasNext()) {
                        throw new NoSuchElementException();
                    }
                    try {
                        Map.Entry entry = this.nextOrNull;
                        return entry;
                    } finally {
                        this.toRemove = this.nextOrNull;
                        this.nextOrNull = this.this$1.forward().lowerEntry(this.nextOrNull.getKey());
                    }
                }

                @Override
                public void remove() {
                    CollectPreconditions.checkRemove(this.toRemove != null);
                    this.this$1.forward().remove(this.toRemove.getKey());
                    this.toRemove = null;
                }

                @Override
                public Object next() {
                    return this.next();
                }
            };
        }
    }
}

