/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facepapersampleimpl;

/**
 *
 * @author Usuario
 */
public class UtilMatrices {
    public static void imprimirMatriz(int[][] matriz) {
        for(int i = 0;i < matriz.length; i++) {
            for(int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] == Integer.MAX_VALUE){
                    System.out.print(" INF ");
                }
                else{
                    System.out.print(matriz[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
