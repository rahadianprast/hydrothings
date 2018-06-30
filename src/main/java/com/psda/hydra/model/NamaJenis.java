package com.psda.hydra.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum NamaJenis {
   PDA("PDA"), PCH("PCH"), KLM("KLM");
   private String getType;

   NamaJenis(String getType){
       this.getType = getType;
   }

    @Override
    public String toString() {
        return String.valueOf(getType);
    }

    @JsonCreator
    public static NamaJenis fromValue(String text){
       for (NamaJenis a: NamaJenis.values()){
           if (String.valueOf(a.getType).equals(text)){
               return a;
           }
       }
       return null;
    }
}
