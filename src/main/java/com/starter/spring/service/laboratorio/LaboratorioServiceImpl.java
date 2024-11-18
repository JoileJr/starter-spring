package com.starter.spring.service.laboratorio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.starter.spring.dto.models.EnderecoDTO;
import com.starter.spring.dto.models.LaboratorioDTO;
import com.starter.spring.dto.useCases.LaboratorioCreateRequest;
import com.starter.spring.exceptions.DataIntegrityViolationException;
import com.starter.spring.exceptions.ObjectnotFoundException;
import com.starter.spring.model.Endereco;
import com.starter.spring.model.Laboratorio;
import com.starter.spring.model.Pessoa;
import com.starter.spring.repository.EnderecoRepository;
import com.starter.spring.repository.LaboratorioRepository;
import com.starter.spring.repository.PessoaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LaboratorioServiceImpl implements LaboratorioService {

    private final LaboratorioRepository laboratorioRepository;

    private final EnderecoRepository enderecoRepository;

    private final PessoaRepository pessoaRepository;

    @Override
    public LaboratorioDTO findById(Long id) {
        Optional<Laboratorio> obj = laboratorioRepository.findById(id);
        return LaboratorioDTO
                .toDTO(obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id)));
    }

    @Override
    public List<LaboratorioDTO> findAll() {
        List<Laboratorio> laboratorios = laboratorioRepository.findAll();
        return laboratorios.stream()
                .map(LaboratorioDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public LaboratorioDTO create(LaboratorioCreateRequest objDTO) {
        validateByEmailAndCpf(objDTO);
        objDTO.setEndereco(registerAddress(objDTO.getEndereco()));
        Laboratorio laboratorio = LaboratorioCreateRequest.toEntity(objDTO);
        Pessoa pessoa = findAdmById(objDTO.getIdAdm());
        laboratorio = laboratorioRepository.save(laboratorio);
        pessoa.setLaboratorio(laboratorio);
        pessoaRepository.save(pessoa);
        return LaboratorioDTO.toDTO(laboratorio);
    }

    @Transactional
    @Override
    public LaboratorioDTO update(Long Id, LaboratorioDTO objDTO) {
        objDTO.setId(Id);
        objDTO.setEndereco(registerAddress(objDTO.getEndereco()));
        Laboratorio laboratorio = LaboratorioDTO.toEntity(objDTO);
        laboratorio = laboratorioRepository.save(laboratorio);
        return LaboratorioDTO.toDTO(laboratorio);
    }

    private void validateByEmailAndCpf(LaboratorioCreateRequest objDTO) {
        Optional<Laboratorio> obj = laboratorioRepository.findByCnpj(objDTO.getCnpj());
        if (obj.isPresent() && obj.get().getCnpj().equals(objDTO.getCnpj())) {
            throw new DataIntegrityViolationException("CNPJ já cadastrado no sistema!");
        }
    }

    private EnderecoDTO registerAddress(EnderecoDTO enderecoDTO) {
        if (enderecoDTO == null) {
            throw new DataIntegrityViolationException("Endereço é obrigatorio");
        }
        Endereco endereco = enderecoRepository.save(EnderecoDTO.toEntity(enderecoDTO));
        return EnderecoDTO.toDTO(endereco);
    }

    private Pessoa findAdmById(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (!pessoa.isPresent()) {
            throw new DataIntegrityViolationException("Não é possivel cadastrar um laboratorio sem Dono!");
        }
        return pessoa.get();
    }

}
