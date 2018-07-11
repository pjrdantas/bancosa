package br.com.meuBanco.service.impl;

import java.util.List;

import br.com.meuBanco.entity.TbNota;
import br.com.meuBanco.entity.dto.TbNotaDTO;





public interface ITbNotaService {
	
    void addTbNota(TbNota tbNota);
    void updateTbNota(TbNota tbNota);	
    List<TbNotaDTO> consultar();
    TbNotaDTO getTbNotaById(int id);
    void deleteTbNota(int id);
}
