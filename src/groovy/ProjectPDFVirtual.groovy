package com.opencsi.ghrm.domain

class ProjectPDFVirtual {
    String projectName
    TreeMap<Date,Float> data = new TreeMap<Date,Float>()
    float max
    float total  
    boolean show = false
    
    def setMax(float max){
        this.max = max
    }
    
    def setTotal(float total){
        this.total = total
    }
    
    def setPName(String PName){
        this.projectName = PName
    }
    
    def addTask(Date date,Float value){
        data.put(date,value)
    }
    
    def isShow(){
        this.show = true
    }
    
    boolean isToShow(){
        return this.show
    }
}
