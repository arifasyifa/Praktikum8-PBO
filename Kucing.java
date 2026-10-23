package com.praktikum8;

public class Kucing extends Hewan {
    @Override // Anotasi opsional tapi direkomendasikan
    public void bersuara() {
        System.out.println("Kucing: Meow meow!");
    }
}