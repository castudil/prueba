package ajedrez;
import java.io.IOException;
import java.util.Scanner;


/**
 *Clase para proyecto de la primera tarea
 *
 **/
public class Ajedrez {
    
    //Función que lee la pieza
      public static String leerpieza() {
         String pieza;        
         int x;
         do{
             System.out.println("Ingrese pieza:");
             Scanner leer = new  Scanner(System.in );
             pieza=leer.nextLine();
              x=pieza.length();
         }while (x!=1 || pieza.charAt(0)!='p' && pieza.charAt(0)!='P' 
                 && pieza.charAt(0)!='n' && pieza.charAt(0)!='N'
                 && pieza.charAt(0)!='b' && pieza.charAt(0)!='B'
                 && pieza.charAt(0)!='r' && pieza.charAt(0)!='R'
                 && pieza.charAt(0)!='q' && pieza.charAt(0)!='Q'
                 && pieza.charAt(0)!='k' && pieza.charAt(0)!='K');                  
        return(pieza);
     }
    
    
    //Función que lee la fila
     public static int leerfila() {
         int fila;
         Scanner leer = new  Scanner(System.in );
         do{
            System.out.println("Ingrese fila:");
            fila=leer.nextInt(); 
        } while( fila<1 || fila>8 );
        fila=8-fila;
        return(fila);    
     }

     //Función que lee la columna
     public static int leercolumna() {
         String columna;        
         int x;
         do{
             System.out.println("Ingrese columna(Mayúscula):");
             Scanner leer = new  Scanner(System.in );
             columna=leer.nextLine();
              x=columna.length();
         }while (x!=1 || columna.codePointAt(0)>72 || columna.codePointAt(0)<65 );                  
        return(columna.codePointAt(0)-65);
     }
     
     
     //Función inicializacion
    public static char[][] creardatos(int fila, int columna, String pieza ) {
         char matriz[][]= new char[8][8];
         for (int i=0; i<8;i++){
             for (int j=0; j<8;j++){
                 matriz[i][j]='.';
             }
         }
            matriz[fila][columna]=pieza.charAt(0);
        return( matriz);
    }

      //Función que imprime por pantalla
    private static void imprimir(char[][] matriz) {
        
         for (int i=0; i<8;i++){
             for (int j=0; j<8;j++){
              System.out.print(matriz[i][j]+" ");
             }
        System.out.println();
    }
    }
    
    //Función que marca los posibles movimientos
    public static char[][] movimiento (int fila, int columna, String pieza,char[][] matriz ) {
        //Movimiento peón blanco
        if(pieza.charAt(0)=='P'){
            if(izquierda(columna) && arriba(fila)) matriz[fila-1][columna-1]='*';
            if(derecha(columna) && arriba(fila)) matriz[fila-1][columna+1]='*';                        
            }               
        //Movimiento peón negro
        if(pieza.charAt(0)=='p'){
            if(izquierda(columna) && abajo(fila)) matriz[fila+1][columna-1]='*';
            if(derecha(columna) && abajo(fila)) matriz[fila+1][columna+1]='*';                        
            }      
        //Movimientos de la torre 
        if(pieza.charAt(0)=='r' || pieza.charAt(0)=='R' ){
             for (int i=0; i<8;i++){
                 for (int j=0; j<8;j++){
                     if(arriba(i)) matriz[i-1][columna]='*';
                     if(abajo(i)) matriz[i+1][columna]='*';
                     if(derecha(j)) matriz[fila][j+1]='*';
                     if(izquierda(j)) matriz[fila][j-1]='*';
                 }
             matriz[fila][columna]=pieza.charAt(0);  
             }
          }
         //Movimientos del alfil
         if(pieza.charAt(0)=='b' || pieza.charAt(0)=='B'){
             for (int i=0; i<8;i++){
                 for (int j=0; j<8;j++){
                     if(Math.abs(fila-i)==Math.abs(columna-j)) matriz[i][j]='*';
                     }
             matriz[fila][columna]=pieza.charAt(0);  
             }
          }
        //Movimiento de la Reina
         if(pieza.charAt(0)=='q' || pieza.charAt(0)=='Q'){
             for (int i=0; i<8;i++){
                 for (int j=0; j<8;j++){
                     if(Math.abs(fila-i)==Math.abs(columna-j)) matriz[i][j]='*';
                     if(arriba(i)) matriz[i-1][columna]='*';
                     if(abajo(i)) matriz[i+1][columna]='*';
                     if(derecha(j)) matriz[fila][j+1]='*';
                     if(izquierda(j)) matriz[fila][j-1]='*';                 
                 }
             matriz[fila][columna]=pieza.charAt(0);  
             }
          }
        //Movimiento Rey 
         if(pieza.charAt(0)=='k' || pieza.charAt(0)=='K'){
            if(izquierda(columna) && arriba(fila)) matriz[fila-1][columna-1]='*';
            if(derecha(columna) && arriba(fila)) matriz[fila-1][columna+1]='*'; 
            if(izquierda(columna) && abajo(fila)) matriz[fila+1][columna-1]='*';
            if(derecha(columna) && abajo(fila)) matriz[fila+1][columna+1]='*';
            if(arriba(fila)) matriz[fila-1][columna]='*';
            if(abajo(fila)) matriz[fila+1][columna]='*';
            if(derecha(columna)) matriz[fila][columna+1]='*';
            if(izquierda(columna)) matriz[fila][columna-1]='*';
         }
         //Movimiento del caballo
         if(pieza.charAt(0)=='n' || pieza.charAt(0)=='N'){
           if(arriba(fila) && izquierda((columna-1))) matriz[fila-1][columna-2]='*';
           if(arriba(fila-1)&& izquierda(columna))matriz[fila-2][columna-1]='*';
           if(arriba(fila-1)&& derecha(columna)) matriz[fila-2][columna+1]='*';
           if(arriba(fila)&& derecha(columna+1))matriz[fila-1][columna+2]='*';           
           if(abajo(fila)&& izquierda(columna-1)) matriz[fila+1][columna-2]='*';
           if(abajo(fila+1)&& izquierda(columna))matriz[fila+2][columna-1]='*';
           if(abajo(fila+1)&& derecha(columna)) matriz[fila+2][columna+1]='*';
           if(abajo(fila)&& derecha(columna+1))matriz[fila+1][columna+2]='*';
         }
         
        return( matriz);
    }
     //Evalúa el limite izquierdo
    public static boolean izquierda( int columna) {
         if(columna<1){
             return (false);
         }
         else return(true);        
    }
      //Evalúa el limite derecho
      public static boolean derecha( int columna) {
         if(columna>6){
             return (false);
         }
         else return(true);        
    }
    //Evalúa el limite de arriba
      public static boolean arriba( int fila) {
         if(fila<1){
             return (false);
         }
         else return(true);        
    }  
    //Evalúa el limite de abajo
      public static boolean abajo( int fila) {
         if(fila>6){
             return (false);
         }
         else return(true);        
    }
      
      
    public static void main(String[] args) throws IOException {
        
       int fila,columna;
       char matriz[][];
       String pieza;       
      
       pieza=leerpieza();            
       fila =leerfila();
       columna=leercolumna();
       matriz= creardatos(fila,columna,pieza);
       movimiento(fila,columna,pieza,matriz);
       imprimir(matriz);
       
       }

   
    
}
 