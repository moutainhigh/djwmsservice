package com.djcps.wms.commons.enums;

public enum FluteTypeEnum1 {
    /**
     * 楞型
        "BC瓦"= 1
        "BE瓦"= 2
        "单C瓦"= 3
        "单B瓦"= 4
        "单E瓦"= 5
        "EBC瓦"= 6
        "EE瓦"= 7
        B 2.5-3.0mm  折中2.75mm
        C 3.5-4.0mm  折中3.75mm
        E 1.1-2.0mm  折中1.55mm
        
     */
    
    BC(1, 6.5, "BC瓦"),
    BE(2, 4.3, "BE瓦"),
    C(3, 3.75, "单C瓦"),
    B(4, 2.75, "单B瓦"),
    E(5, 1.55, "单E瓦"),
    EBC(6, 8.05, "EBC瓦"),
    EE(7, 3.1, "EE瓦")
    ;
    
    private int code;
    
    private double thickness;
    
    private String name;
    
    FluteTypeEnum1(int code, double thickness, String name){
        this.code = code;
        this.thickness = thickness;
        this.name = name;
    }
    
    public static double getThickness(int code){
        for(FluteTypeEnum1 e : FluteTypeEnum1.values()){
            if(e.code == code){
                return e.thickness;
            }
        }
        return 0.0;
    }
    
    public static int getCode(String name){
        for(FluteTypeEnum1 e : FluteTypeEnum1.values()){
            if(e.name.equals(name)){
                return e.code;
            }
        }
        return 0;
    }
    
    public static String getName(int code){
        for(FluteTypeEnum1 e : FluteTypeEnum1.values()){
            if(e.code == code){
                return e.name;
            }
        }
        return "";
    }
    
    /**
     * 获取枚举中最小厚度值
     * @return
     */
    public static double minThickness(){
        double minThickness = Double.MAX_VALUE;
        for(FluteTypeEnum1 e : FluteTypeEnum1.values()){
            if(e.thickness < minThickness){
                minThickness = e.thickness;
            }
        }
        return minThickness;
    }
    
}
