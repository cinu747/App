/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.web;

import edu.iit.sat.itmd4515.ckolathabraham.domain.Hospital;
import edu.iit.sat.itmd4515.ckolathabraham.service.HospitalService;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Cini
 */
@FacesConverter(value = "hospitalConverter", managed = true)
public class HospitalConverter implements Converter<Hospital> {

    @EJB
    HospitalService hospSvc;

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public Hospital getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return hospSvc.read(Long.valueOf(value));
    }

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Hospital value) {
        if (value == null) {
            return "";
        }
        return String.valueOf(value.getId());
    }

}
