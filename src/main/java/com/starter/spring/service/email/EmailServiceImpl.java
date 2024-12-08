package com.starter.spring.service.email;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.starter.spring.dto.models.ExameDTO;
import com.starter.spring.service.exames.ExamesService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final ExamesService examesService;

    @Override
    public void enviarEmailComAnexo(Long id) throws MessagingException {
        ExameDTO exameDTO = this.examesService.listarExamesPorID(id);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        String nomePaciente = exameDTO.getPaciente().getNome();
        String nomeLaboratorio = exameDTO.getLaboratorio().getNome();
        String LaboratorioContato = exameDTO.getLaboratorio().getTelefone() + ", "
                + exameDTO.getLaboratorio().getEmail();

        String mensagem = "Prezado(a) " + nomePaciente + ",\n\n"
                + "Esperamos que você esteja bem.\n\n"
                + "Estamos enviando em anexo o resultado do seu exame . "
                + "Por favor, revise o documento para obter todas as informações necessárias.\n\n"
                + "Caso tenha qualquer dúvida ou precise de mais esclarecimentos, não hesite em entrar em contato conosco.\n\n"
                + "Atenciosamente,\n"
                + nomeLaboratorio + "\n"
                + LaboratorioContato;

        if (exameDTO.getPaciente().getEmail() == null) {
            return;
        }

        helper.setTo(exameDTO.getPaciente().getEmail());
        helper.setSubject("Resultado de Exame Disponível");
        helper.setText(mensagem);
        ByteArrayResource pdfAnexo = new ByteArrayResource(this.examesService.exportarPDF(exameDTO.getId()));
        String nomeArquivoPdf = "resultado_exame_" + exameDTO.getPaciente().getNome().replace(" ", "_") + "_"
                + exameDTO.getId() + ".pdf";
        helper.addAttachment(nomeArquivoPdf, pdfAnexo);
        mailSender.send(mimeMessage);
    }

}
