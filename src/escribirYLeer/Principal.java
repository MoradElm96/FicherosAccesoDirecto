/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escribirYLeer;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Morad
 */
public class Principal {

    public static void main(String[] args) {
        Producto producto;

        //RandomAccesFile, para leer dato determinado
        ArrayList<Producto> listaProductos = new ArrayList();

        listaProductos.add(new Producto(1, "Producto 1", 10.5, true, 'T'));
        listaProductos.add(new Producto(2, "Producto 2", 50.5, true, 'R'));
        listaProductos.add(new Producto(3, "Producto 3", 10.5, false, 'T'));
        listaProductos.add(new Producto(4, "Producto 4", 30.5, true, 'B'));
        listaProductos.add(new Producto(5, "Producto 5", 20.5, true, 'T'));

        File f = new File("productos.bin");
        try {//si ponemos el raf dentro del try estamos autocerrando
            //rw o r, lectura escritura o solo lectura.

            RandomAccessFile raf = new RandomAccessFile(f, "rw");

            for (Producto p : listaProductos) {

                raf.writeInt(p.getId());

                //para la cadena
                //es importante que sea limitado
                StringBuffer sb = new StringBuffer(p.getNombre());
                sb.setLength(10);// si es mas de 10 caracteres se corta y si es menos se pone espacio.

                raf.writeChars(sb.toString());

                raf.writeDouble(p.getPrecio());
                raf.writeBoolean(p.isDescuento());
                raf.writeChar(p.getTipo());

            }

            /*int son 4 bytes
            string 2 bytes por cada caracter
            double son 8 bytes
            boolean es 1 byte
            
            4+20+8+1+2 =35 bytes cada registro
            
            si me quiero posicionar en el segundo
            raf.seek(35); 35* el que quiero -1
            
            el byte *2 -1
             */
            //leemos el segundo registro
            /* raf.seek(35);
            
            System.out.println(raf.readInt());
            //para el string
            
            String nombre = "";
            //10 son los caracteres
            for (int i = 0; i < 10; i++) {
                
                nombre+= raf.readChar();
                
            }
            
            System.out.println(nombre);
            System.out.println(raf.readDouble());
            System.out.println(raf.readBoolean());
            System.out.println(raf.readChar());*/
            System.out.println("Introduce posicion a leer");
            Scanner sc = new Scanner(System.in);

            int posicion = sc.nextInt();
            leerFicheroRandom(raf, posicion);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }

    }

    public static void leerFicheroRandom(RandomAccessFile raf, int posicion) throws IOException {

        raf.seek(posicion);

        System.out.println(raf.readInt());
        //para el string

        String nombre = "";
        //10 son los caracteres
        for (int i = 0; i < 10; i++) {

            nombre += raf.readChar();

        }

        System.out.println(nombre);
        System.out.println(raf.readDouble());
        System.out.println(raf.readBoolean());
        System.out.println(raf.readChar());

        //se tiene que leer igual que se escribe
    }

}
