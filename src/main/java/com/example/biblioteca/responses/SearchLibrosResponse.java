package com.example.biblioteca.responses;

import com.example.biblioteca.dtos.SearchLibrosElementDTO;

import java.util.ArrayList;
import java.util.List;

public class SearchLibrosResponse {

    List<SearchLibrosElementDTO> elements;

    public SearchLibrosResponse() {
        this.elements = new ArrayList<SearchLibrosElementDTO>();
    }

    public void addElement(SearchLibrosElementDTO element){
        elements.add(element);
    }

    public List<SearchLibrosElementDTO> getElements() {
        return elements;
    }
}
