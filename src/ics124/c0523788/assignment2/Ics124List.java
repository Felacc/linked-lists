/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ics124.c0523788.assignment2;

/**
 *
 * @author felix
 */
public interface Ics124List<T> {
    public int size();
    public T get(int i);
    public void set(int i, T x);
    public void add (int i, T x);
    public T remove(int i);
}
