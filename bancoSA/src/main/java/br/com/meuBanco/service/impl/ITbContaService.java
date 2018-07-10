package br.com.meuBanco.service.impl;

import java.util.List;

import br.com.meuBanco.entity.TbConta;
import br.com.meuBanco.entity.dto.TbContaDTO;





public interface ITbContaService {
	
    void addTbConta(TbConta tbConta);
    void updateTbConta(TbConta tbConta);	
    List<TbContaDTO> consultar();
    TbContaDTO getTbContaById(int id);
    void deleteTbConta(int id);
}
