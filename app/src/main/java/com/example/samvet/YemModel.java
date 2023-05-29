package com.example.samvet;

     class YemModel {
        private String yemadi;
        int image;
        double oran,result;


         public YemModel(String yemadi, int image, double oran, double result) {
             this.yemadi = yemadi;
             this.image = image;
             this.oran = oran;
             this.result = result;
         }

         public YemModel(String yemadi, int image) {
             this.yemadi= yemadi;
             this.image= image;
         }

         public String getYemadi() {
             return yemadi;
         }

         public int getImage() {
             return image;
         }

         public double getOran() {

             return oran;
         }

         public double getResult() {
             return result;
         }
     }
