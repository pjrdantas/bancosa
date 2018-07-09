package br.com.meuBanco.service.impl;

import java.util.List;

import br.com.meuBanco.entity.TbAgencia;
import br.com.meuBanco.entity.dto.TbAgenciaDTO;





public interface ITbAgenciaService {
	
    void addTbAgencia(TbAgencia tbAgencia);
    void updateTbAgencia(TbAgencia tbAgencia);	
    List<TbAgenciaDTO> consultar();
    TbAgenciaDTO getTbAgenciaById(int id);
    void deleteTbAgencia(int id);
}
