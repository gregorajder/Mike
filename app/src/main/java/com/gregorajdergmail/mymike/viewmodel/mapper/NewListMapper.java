//package com.gregorajdergmail.mymike.vm.mapper;
//
//import com.gregorajdergmail.mymike.model.dto.NewListDTO;
//import com.gregorajdergmail.mymike.model.dto.NewListItemDTO;
//import com.gregorajdergmail.mymike.vm.viewObject.NewListItemVO;
//import com.gregorajdergmail.mymike.vm.viewObject.NewListVO;
//
//import java.util.ArrayList;
//
//import rx.functions.Func1;
//
//public class NewListMapper implements Func1<NewListDTO, NewListVO> {
//    private NewListVO newListVO;
//
//    public NewListMapper(NewListVO newListVO) {
//        this.newListVO = newListVO;
//    }
//
//    @Override
//    public NewListVO call(NewListDTO newListDTO) {
//        newListVO.setName( newListDTO.getName());
//        ArrayList<NewListItemVO> list = new ArrayList<NewListItemVO>();
//
//        ArrayList<NewListItemDTO> forecastItemList = (ArrayList<NewListItemDTO>) newListDTO.getList();
//        for (NewListItemDTO newListItemDTO : forecastItemList) {
//
//            NewListItemVO newListItemVO = new NewListItemVO();
//            newListItemVO.setTitle( newListItemDTO.getTitle() );
//            newListItemVO.setDescription(newListItemDTO.getDescription());
//            newListItemVO.setId( newListItemDTO.getId() );
//            newListItemVO.setImageUrl( newListItemDTO.getImageUrl());
//
//            list.add(newListItemVO);
//        }
//
//        newListVO.setList(list);
//        return newListVO;
//    }
//}
