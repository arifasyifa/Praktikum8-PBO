Modul 8 Praktikum Pemrograman Berorientasi Objek (PBO)

Repository ini berisi implementasi kode program untuk Modul 8 Praktikum Pemrograman Berorientasi Objek dengan fokus pembahasan pada Polymorphism, Abstract Class, dan Interface.

A. Tujuan Pembelajaran
Setelah menyelesaikan modul praktikum ini, mahasiswa diharapkan mampu:
1. Memahami dan mengimplementasikan konsep Polymorphism (polimorfisme) pada tingkat runtime (Method Overriding).
2. Merancang Abstract Class dan Abstract Method sebagai kerangka dasar dari sebuah hierarki kelas.
3. Mengimplementasikan Interface untuk menciptakan kontrak arsitektur perangkat lunak dan menyimulasikan pewarisan ganda (multiple inheritance).

B. Dasar Teori
1. Polymorphism: Berasal dari bahasa Yunani yang berarti "banyak bentuk". Dalam OOP, sebuah objek dapat memiliki berbagai bentuk perilaku meskipun dipanggil dari referensi kelas induk yang sama. Terdapat dua jenis polimorfisme: Compile-time (Method Overloading) dan Runtime (Method Overriding).
2. Abstract Class: Sebuah kelas yang dideklarasikan dengan kata kunci abstract. Kelas ini tidak dapat diinstansiasi (tidak bisa dibuat objeknya menggunakan new), namun dapat diwariskan ke subclass. Kelas ini biasanya berisi satu atau lebih abstract method (metode tanpa isi/bodi) yang wajib di-override oleh subclass-nya.
3. Interface: Sebuah cetak biru murni (pure abstraction) dari kelas yang hanya berisi tanda tangan metode (method signatures) dan konstanta. Berbeda dengan class yang hanya bisa mewarisi (extends) satu kelas lain, sebuah kelas di Java dapat mengimplementasikan (implements) lebih dari satu Interface.

C. Kegiatan Praktikum & Penjelasan Kode
1. Kegiatan 1: Implementasi Polymorphism (Method Overriding)
Konsep utama di sini adalah Polimorfisme Runtime (Method Overriding), di mana sebuah objek dapat memiliki perilaku yang berbeda meskipun dipanggil melalui referensi dari kelas induk yang sama.
Penjelasan:
- Hewan.java (Superclass): Kelas ini berfungsi sebagai kelas induk dasar yang mendefinisikan method bersuara() secara umum (default).
- Kucing.java & Anjing.java (Subclass): Kedua kelas ini mewarisi kelas Hewan (extends Hewan). Mereka menggunakan anotasi @Override untuk menulis ulang (override) method bersuara() agar sesuai dengan karakteristik masing-masing (Kucing mengeluarkan suara "Meow meow!" dan Anjing "Guk guk!").
- PoliTester.java (Program Utama):
-- Kelas ini mendemonstrasikan Dynamic Binding.
-- Array dibuat dengan tipe data superclass (Hewan[] daftarHewan = new Hewan[3];), namun diisi dengan objek-objek dari subclass-nya (new Kucing() dan new Anjing()) melalui proses yang disebut Upcasting.
-- Ketika perulangan (for) memanggil h.bersuara(), Java secara otomatis mengenali bentuk objek aslinya saat runtime dan mengeksekusi method bersuara() yang spesifik milik Kucing atau Anjing.

Kode Program:
File: Hewan.java
package com.praktikum8;
public class Hewan {
    public void bersuara() {
        System.out.println("Hewan ini mengeluarkan suara yang tidak spesifik.");
    }
}


File: Kucing.java

package com.praktikum8;
public class Kucing extends Hewan {
    @Override 
    public void bersuara() {
        System.out.println("Kucing: Meow meow!");
    }
}


File: Anjing.java

package com.praktikum8;
public class Anjing extends Hewan {
    @Override
    public void bersuara() {
        System.out.println("Anjing: Guk guk!");
    }
}


File: PoliTester.java

package com.praktikum8;
public class PoliTester {
    public static void main(String[] args) {
        // Deklarasi array dari tipe Superclass, tetapi diisi dengan objek Subclass
        Hewan[] daftarHewan = new Hewan[3];
        daftarHewan[0] = new Hewan();
        daftarHewan[1] = new Kucing(); // Upcasting
        daftarHewan[2] = new Anjing(); // Upcasting

        // Polimorfisme: Pemanggilan method yang sama menghasilkan output berbeda
        for (Hewan h : daftarHewan) {
            h.bersuara();
        }
    }
}


2. Kegiatan 2: Implementasi Abstract Class
Abstract class adalah kelas yang tidak bisa diinstansiasi secara langsung (tidak bisa dibuat objeknya menggunakan new), tetapi dirancang sebagai kerangka dasar bagi kelas turunannya.
Penjelasan:
- BangunDatar.java (Abstract Class):
-- Memiliki kata kunci abstract.
-- Berisi atribut warna, konstruktor untuk inisialisasi warna, serta Abstract Method hitungLuas() (metode tanpa bodi/isi yang wajib di-override oleh anak kelasnya).
-- Juga berisi Concrete Method (cetakWarna()), yaitu metode yang sudah memiliki isi dan bisa langsung dipakai oleh subclass tanpa harus ditulis ulang.
- Lingkaran.java (Subclass):
-- Mewarisi BangunDatar dan wajib mengimplementasikan method hitungLuas().
-- Di dalam method tersebut, luas dihitung menggunakan rumus $\pi \times jariJari \times jariJari$ (Math.PI * jariJari * jariJari). Jika method ini tidak dibuat di subclass, IDE Java akan otomatis menampilkan pesan error.

Kode Program:
File: BangunDatar.java
public abstract class BangunDatar {
    String warna;

    public BangunDatar(String warna) {
        this.warna = warna;
    }

    // Abstract method: Tidak memiliki isi, wajib di-override subclass
    public abstract double hitungLuas();

    // Concrete method: Punya isi, bisa langsung dipakai
    public void cetakWarna() {
        System.out.println("Warna bangun ini adalah: " + warna);
    }
}


File: Lingkaran.java

public class Lingkaran extends BangunDatar {
    private double jariJari;

    public Lingkaran(String warna, double jariJari) {
        super(warna);
        this.jariJari = jariJari;
    }

    @Override
    public double hitungLuas() {
        return Math.PI * jariJari * jariJari;
    }
}


3. Kegiatan 3: Implementasi Interface (Multiple Implementation)
Interface adalah cetak biru murni (pure abstraction) yang hanya berisi kerangka tanda tangan method tanpa implementasi isi. Keunggulan interface dibanding kelas biasa adalah kemampuannya mensimulasikan pewarisan ganda (multiple implementation), di mana satu kelas dapat mengimplementasikan banyak interface sekaligus.
Penjelasan:
- AlatElektronik.java & Kamera.java (Interface):
-- Keduanya adalah antarmuka terpisah.
-- AlatElektronik mendefinisikan kontrak method nyalakan() dan matikan().
-- Kamera mendefinisikan kontrak method ambilFoto().
- Smartphone.java (Implementasi Ganda):
-- Kelas ini menggunakan kata kunci implements AlatElektronik, Kamera.
-- Karena mengimplementasikan dua interface sekaligus, kelas Smartphone wajib mendeklarasikan ulang dan mengisi bodi dari semua method yang ada di kedua interface tersebut (nyalakan, matikan, dan ambilFoto) secara bersamaan.

Kode Program:

File: AlatElektronik.java

public interface AlatElektronik {
    void nyalakan();
    void matikan();
}


File: Kamera.java

public interface Kamera {
    void ambilFoto();
}


File: Smartphone.java

public class Smartphone implements AlatElektronik, Kamera {
    private String merk;

    public Smartphone(String merk) {
        this.merk = merk;
    }

    @Override
    public void nyalakan() {
        System.out.println(merk + " menyala, Booting OS...");
    }

    @Override
    public void matikan() {
        System.out.println(merk + " dimatikan. Goodbye.");
    }

    @Override
    public void ambilFoto() {
        System.out.println("Foto berhasil disimpan di galeri " + merk);
    }
}
