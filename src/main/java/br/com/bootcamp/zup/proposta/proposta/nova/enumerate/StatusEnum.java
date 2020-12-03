package br.com.bootcamp.zup.proposta.proposta.nova.enumerate;

public enum StatusEnum {

    NAO_ELEGIVEL("COM_RESTRICAO"),
    ELEGIVEL("SEM_RESTRICAO");

    public String status;

    StatusEnum(String status){
    this.status = status;
    }

   public static StatusEnum valueOfLabel(String label){
        for(StatusEnum enumerate : values()){
            if(enumerate.status.equals(label)) {
                return enumerate;
            }
       }
        return null;
   }
}
