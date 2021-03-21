package com.company;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyCollection<E> implements Collection<E> {

    private int size;

    private Object[] elementData = new Object[10];

    @Override //done
    public final boolean add(final E e) {
        if (size == elementData.length) {
            elementData = Arrays.copyOf(elementData, (int) (size * 1.5f));
        }
        elementData[size++] = e;
        return true;
    }

    @Override //done
    public final int size() {
        return size;
    }

    @Override //done
    public final boolean isEmpty() {
        return size == 0;
    }

    @Override
    public final Iterator<E> iterator() {
        return new MyIterator<>();
    }

    @Override //done
    public final boolean contains(final Object o) {
        int i = 0;
        boolean m = false;
        while (i < size) {
            if (elementData[i] == o) {
                m = true;
                break;
            } else {
                i++;
            }
        }
        return m;
    }

    @Override // done
    public final Object[] toArray() {
        return Arrays.copyOf(elementData, (int) (size));
    }

    @Override // ?
    public final <T> T[] toArray(T[] a) {
        int i = 0;
        if (a.length >= size) {
            while (i < size) {
                a[i] = (T) elementData[i];
                i++;
            }
        } else {
            a = (T[]) Arrays.copyOf(elementData, (size));
        }
        return a;
    }

    @Override //done
    public final boolean remove(final Object o) {
        Object buffer;
        boolean m = false;
        for (int i = 0; i < size; i++) {
            if (elementData[i] == o) {
                m = true;
                buffer = elementData[i];
                elementData[i] = elementData[i + 1];
                elementData[i + 1] = buffer;
            }
        }
        if (m) {
            elementData = Arrays.copyOf(elementData, (size--));
        }
        return m;

            }

    @Override //done
    public final boolean containsAll(final Collection<?> c) {
        boolean b = false;
        for (int j = 0; j < c.size(); j++) {
            b = contains(c.toArray()[j]);
        }
        return b;
    }

    @Override  //done
    public final boolean addAll(final Collection<? extends E> c) {
        if (size == elementData.length) {
            elementData = Arrays.copyOf(elementData, (int) (size + c.size()));
        }
        for (int i = 0; i < c.size(); i++) {
            elementData[size++] = c.toArray()[i];
        }

        return true;
    }

    @Override //done
    public final boolean removeAll(final Collection<?> c) {
        boolean m = false;
        for (int i = 0; i < c.size(); i++) {
            while (contains(c.toArray()[i])) {
                remove(c.toArray()[i]);
                m = true;
            }
        }
        return m;
    }

    @Override // done
    public final boolean retainAll(final Collection<?> c) {

        boolean m = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                m = true;
            }
        }
        return m;
    }

    @Override // done
    public final void clear() {
        removeAll(Arrays.asList(elementData));
    }

    private class MyIterator<T> implements Iterator<T> {

        private int cursor = 0;

        @Override // done
        public boolean hasNext() {
            return cursor < size;
        }

        @Override // done
        @SuppressWarnings("unchecked")
        public T next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            return (T) elementData[cursor++];
        }

        @Override // done
        public void remove() {
            if (cursor >= 1) {
                for (int i = 0; i < size - cursor; i++) {
                    Object buffer;
                    buffer = elementData[cursor - 1 + i];
                    elementData[cursor - 1 + i] = elementData[cursor + i];
                    elementData[cursor + i] = buffer;
                }
                elementData = Arrays.copyOf(elementData, (size--));
                cursor--;
                //throw new UnsupportedOperationException("remove");
            }
        }
    }
}
