package org.example.service;

import org.example.accessor.ShowAccessor;
import org.example.accessor.models.ShowDTO;
import org.example.controller.models.ShowOutput;
import org.example.mapper.ShowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShowService {
    @Autowired
    private ShowAccessor showAccessor;

    @Autowired
    private ShowMapper showMapper;

    public List<ShowOutput> getShowsByName(final String showName){
        List<ShowDTO> showDTOList=showAccessor.getShowsByName(showName);
        List<ShowOutput> showOutputList=new ArrayList<>();
        for(ShowDTO showDTO:showDTOList){
            showOutputList.add(showMapper.mapShowDtoToOutput(showDTO));
        }
        return showOutputList;
    }
}
