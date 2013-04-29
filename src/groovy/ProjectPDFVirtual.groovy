package com.opencsi.ghrm.domain

class ProjectPDFVirtual {
    String projectName,color
    TreeMap<Integer,Float> data = new TreeMap<Integer,Float>()
    
    def setPName(String PName){
        this.projectName = PName
    }
    
    def setColor(String color){
        this.color = color
    }
    
    def addTask(Integer i,Float value){
        data.put(i,value)
    }
}
