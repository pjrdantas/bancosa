package br.com.meuBanco.service.impl;

import java.util.List;

import br.com.meuBanco.entity.dto.TbAgenciaDTO;





public interface ITbAgenciaService {
	
    void addTbAgenciaDTO(TbAgenciaDTO tbAgenciaDTO)   throws Exception, Throwable;
    void updateTbAgenciaDTO(TbAgenciaDTO tbAgenciaDTO)  throws Exception, Throwable;	
    List<TbAgenciaDTO> consultar()  throws Exception, Throwable;
    TbAgenciaDTO getTbAgenciaById(int id)  throws Exception, Throwable;
    void deleteTbAgencia(int id)  throws Exception, Throwable;
}
