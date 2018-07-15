package br.com.meuBanco.service.impl;

import java.util.List;

import br.com.meuBanco.entity.dto.TbBancoDTO;





public interface ITbBancoService {
	
    void addTbBancoDTO(TbBancoDTO tbBancoDTO)  throws Exception, Throwable;
    void updateTbBancoDTO(TbBancoDTO tbBancoDTO)  throws Exception, Throwable;	
	List<TbBancoDTO> getAllTbBancos()  throws Exception, Throwable;
    TbBancoDTO getTbBancoById(int id)  throws Exception, Throwable;
    void deleteTbBanco(int id)  throws Exception, Throwable;
}
