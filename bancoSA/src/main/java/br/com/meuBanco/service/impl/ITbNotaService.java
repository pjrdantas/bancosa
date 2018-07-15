package br.com.meuBanco.service.impl;

import java.util.List;

import br.com.meuBanco.entity.dto.TbNotaDTO;





public interface ITbNotaService {
	
    void addTbNotaDTO(TbNotaDTO tbNotaDTO)  throws Exception, Throwable;
    void updateTbNotaDTO(TbNotaDTO tbNotaDTO)  throws Exception, Throwable;	
    List<TbNotaDTO> consultar()  throws Exception, Throwable;
	List<TbNotaDTO> getTbNotas()  throws Exception, Throwable;
    TbNotaDTO getTbNotaById(int id)  throws Exception, Throwable;
    void deleteTbNota(int id)  throws Exception, Throwable;
}
