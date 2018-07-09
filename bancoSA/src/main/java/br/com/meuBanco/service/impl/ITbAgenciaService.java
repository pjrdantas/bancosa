package br.com.meuBanco.service.impl;

import java.util.List;

import br.com.meuBanco.entity.TbAgencia;





public interface ITbAgenciaService {
	
    void addTbAgencia(TbAgencia tbAgencia);
    void updateTbAgencia(TbAgencia tbAgencia);	
    List<TbAgencia> consultar();
    TbAgencia getTbAgenciaById(int id);
    void deleteTbAgencia(int id);
}
