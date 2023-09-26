/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmeans;

import static java.lang.Math.random;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Bacs
 */
public class Pract2 {
    public int limite;

    public double[][] aleatorios(int Np){
        
        Random rnd = new Random();
        DecimalFormat formato = new DecimalFormat("#.##");
        double[][] puntos = new double[Np][3];
        for(int i=0; i<Np;i++){
            puntos[i][0]=Double.parseDouble(formato.format(rnd.nextDouble()*limite-limite/2));
            puntos[i][1]=Double.parseDouble(formato.format(rnd.nextDouble()*limite-limite/2));
        }
        return puntos;
    }
    
    public double[] cord(double[][] puntos, int Ni, int eje){
        double[] array=new double[Ni];
        for (int i=0;i<Ni;i++){
            array[i]=puntos[i][eje];
        }
        return array;
    }
    
    public double[][] calc_atrac(int Na){
        Random rnd = new Random();

        double angulo = Math.toRadians(360/Na);
        DecimalFormat formato = new DecimalFormat("#.##");
        double[][] puntos = new double[Na][3];
        for(int i=1; i<Na+1;i++){
            
                puntos[i-1][0]=Double.parseDouble(formato.format((Math.cos(angulo*i)*limite)/3));
                puntos[i-1][1]=Double.parseDouble(formato.format((Math.sin(angulo*i)*limite)/3));
            
        }
        
        return puntos;
    }
    
    public double cal_dist(double x1,double y1,double x2,double y2){
        double d=0;
            d=Math.sqrt((Math.pow(x2-x1,2))+(Math.pow(y2-y1,2)));
        return d;
    }
    
    public double[][] clasifica(double puntos[][], double [][] atractores) {
        double[][] puntos2 = new double[puntos.length][3];
        puntos2=puntos;
        int c;
        for(int p=0;p<puntos.length;p++){
            double d1=cal_dist(puntos[p][0], puntos[p][1], atractores[0][0], atractores[0][1]);
            c=0;
            for(int a=0;a<atractores.length;a++){
                double d2=cal_dist(puntos[p][0], puntos[p][1], atractores[a][0], atractores[a][1]);
                if(d1<=d2){
                    puntos2[p][2]=c;
                }else{
                    d1=d2;
                    c=a;
                    puntos2[p][2]=c;
                }

            }
        }/*for (double[] ints : puntos2) {
            System.out.println(Arrays.toString(ints));
        }*/
        return puntos2;
    }
    
    public double[][] calc_centros(double [][] puntos, int k){
        double[][] centroides=new double[k][3];
         DecimalFormat formato = new DecimalFormat("#.##");
         
        for(int j=0;j<k;j++){
            double x=0,y=0,p=0;
            for(int i=0;i<puntos.length;i++){
                if(j==(int)puntos[i][2]){
                    x+=puntos[i][0];
                    y+=puntos[i][1];
                    p++;
                }
                
            }
            centroides[j][0]=Double.parseDouble(formato.format(x/p));
            centroides[j][1]=Double.parseDouble(formato.format(y/p));
            //centroides[j][0]=x/p;
            //centroides[j][1]=y/p;
            centroides[j][2]=j;
        }
        return centroides;
    }
    
    public double[][] agregar_clase(double[][] puntos, int c){
        int Ni=0,j=0;
        for (int i=0;i<puntos.length;i++){
            if((int)puntos[i][2]==c){
                Ni++;
            }
        }
        double[][] array=new double[Ni][3];
        for (int i=0;i<puntos.length;i++){
            if((int)puntos[i][2]==c){
                array[j][0]=puntos[i][0];
                array[j][1]=puntos[i][1];
                array[j][2]=puntos[i][2];
                j++;
            }
        }
        return array;
    }
}
