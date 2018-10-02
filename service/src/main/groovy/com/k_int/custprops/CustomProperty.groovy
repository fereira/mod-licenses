package com.k_int.custprops

import com.k_int.custprops.PropertyDefinition
import com.k_int.refdata.RefdataValue
import javax.persistence.Transient

/**
 * Custom properties must always follow the naming convention: Owner + CustomProperty, where owner is the
 * name of owner class and be under a domain package . For example LicenceCustomProperty , SubscriptionCustomProperty.
 * Relevant code in PropertyDefinition, createPropertyValue
 */
abstract class CustomProperty implements Serializable{

    @Transient
    def controlledProperties = ['stringValue','intValue','decValue','refValue','note']
    String stringValue
    Integer intValue
    BigDecimal decValue
    RefdataValue refValue
    String note = ""
    
    static mapping = {
       note type: 'text'
    }
    static constraints = {
        stringValue(nullable: true)
        intValue(nullable: true)
        decValue(nullable: true)
        refValue(nullable: true)
        note(nullable: true)
    }

    @Transient
    def getValueType(){
        if(stringValue) return "stringValue"
        if(intValue) return "intValue"
        if(decValue) return "decValue"
        if(refValue) return "refValue"
    }

    @Override
    public String toString(){
        if(stringValue) return stringValue
        if(intValue) return intValue.toString()
        if(decValue) return decValue.toString()
        if(refValue) return refValue.toString()
    }
    def copyValueAndNote(newProp){
        if(stringValue) newProp.stringValue = stringValue
        else if(intValue) newProp.intValue = intValue
        else if(decValue) newProp.decValue = decValue
        else if(refValue) newProp.refValue = refValue
        newProp.note = note
        newProp
    }

    def parseValue(value, type){

        def result
        switch (type){
            case Integer.toString():
                result = Integer.parseInt(value)
                break;
            case String.toString():
                result = value
                break;
            case BigDecimal.toString():
                result = new BigDecimal(value)
                break;
            case org.codehaus.groovy.runtime.NullObject.toString():
                result = null
                break;
            default:
                result = "CustomProperty.parseValue failed"
        }
        return result
    }

  public String getValue() {
    return toString()
  }
}
