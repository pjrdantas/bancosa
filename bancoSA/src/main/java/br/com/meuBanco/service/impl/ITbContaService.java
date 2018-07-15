package br.com.meuBanco.service.impl;

import java.util.List;

import br.com.meuBanco.entity.dto.TbContaDTO;





public interface ITbContaService {
	
    void addTbContaDTO(TbContaDTO tbContaDTO)  throws Exception, Throwable;
    void updateTbContaDTO(TbContaDTO tbContaDTO)  throws Exception, Throwable;	
    List<TbContaDTO> consultar()  throws Exception, Throwable;
    TbContaDTO getTbContaById(int id)  throws Exception, Throwable;
    void deleteTbConta(int id)  throws Exception, Throwable;
}
