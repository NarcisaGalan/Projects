package utcn.ti.proiect_licenta.dto;

import utcn.ti.proiect_licenta.model.Departament;
import utcn.ti.proiect_licenta.model.Facultate;
import utcn.ti.proiect_licenta.model.Universitate;

import java.util.List;

public class FillSelectUFDDTO {

    private List<Universitate> universitateList;
    private List<Facultate> facultateList;
    private List<Departament> departamentList;
    private List<String> titluProiectList;

    public FillSelectUFDDTO(){}

    public FillSelectUFDDTO(List<Universitate> universitateList, List<Facultate> facultateList, List<Departament> departamentList, List<String> titluProiectList) {
        this.universitateList = universitateList;
        this.facultateList = facultateList;
        this.departamentList = departamentList;
        this.titluProiectList = titluProiectList;
    }

    public List<Universitate> getUniversitateList() {
        return universitateList;
    }

    public void setUniversitateList(List<Universitate> universitateList) {
        this.universitateList = universitateList;
    }

    public List<Facultate> getFacultateList() {
        return facultateList;
    }

    public void setFacultateList(List<Facultate> facultateList) {
        this.facultateList = facultateList;
    }

    public List<Departament> getDepartamentList() {
        return departamentList;
    }

    public void setDepartamentList(List<Departament> departamentList) {
        this.departamentList = departamentList;
    }

    public List<String> getTitluProiectList() {
        return titluProiectList;
    }

    public void setTitluProiectList(List<String> titluProiectList) {
        this.titluProiectList = titluProiectList;
    }
}
