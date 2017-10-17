package bean;


import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import util.GenericDao;
import util.JPAUtil;


@ManagedBean
public class MedicoBean extends GenericDao< Medico, Long>{
	
	private Medico medico = new Medico();
	
    public MedicoBean() {
        super(Medico.class);
     }   
		
	public static byte[] arquivo;
    public static String nomeArquivo;
	
	
	/* Método que faz o Upload dos arquivos 
    public void doUpload(FileUploadEvent fileUploadEvent) throws IOException {

        UploadedFile uploadedFile = fileUploadEvent.getFile();

        String fileNameUploaded = uploadedFile.getFileName();s
        long fileSizeUploaded = uploadedFile.getSize();
        System.out.println(uploadedFile);

        // arquivo = uploadedFile.getContents();
        String infoAboutFile = "<br/> Arquivo recebido: <b>" + fileNameUploaded
                + "</b><br/>" + "Tamanho do Arquivo: <b>" + fileSizeUploaded
                + "</b>";
        //Mensagem exibida na tela
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null,
                new FacesMessage("Sucesso", infoAboutFile));

        arquivo = (IOUtils.toByteArray(uploadedFile.getInputstream()));
        nomeArquivo = uploadedFile.getFileName();

        medico.setImagem(arquivo);
        medico.setNomeImagem(fileNameUploaded);
        medico.setNomeImagem(nomeArquivo);

        System.out.println("Arquivo capturado" + arquivo);
        System.out.println("Nome do Arquivo" + nomeArquivo);

    }
    */
	

	
    private UploadedFile upFile;
    
    public UploadedFile getUpFile() {
        return  upFile;
    }
 
    public void setUpFile(UploadedFile file) {
        this.upFile = file;
    }
     

  
    public void upload() throws IOException {
  	  try {
    	
    	File file = new File(diretorioRaizParaArquivos(), upFile.getFileName());

        OutputStream out = new FileOutputStream(file);
        out.write(this.upFile.getContents());
        out.close();
        
        FacesContext.getCurrentInstance().addMessage(
	               null, new FacesMessage("Upload completo", 
	               "O arquivo " + upFile.getFileName() + " foi salvo!"));
	  } catch(IOException e) {
	    FacesContext.getCurrentInstance().addMessage(
	              null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
	  }
	     }
   

    public static List<File> listarARQ() {
        File dir = diretorioRaizParaArquivos();

        return Arrays.asList(dir.listFiles());
    }
    
    public static java.io.File diretorioRaizParaArquivos() {
        File dir = new File(diretorioRaiz(), "arquivos");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir;
    }
    
    public static File diretorioRaiz() {
        // Estamos utilizando um diretório dentro da pasta temporária. 
        // No seu projeto, imagino que queira mudar isso para algo como:
        // File dir = new File(System.getProperty("user.home"), "algaworks");
        File dir = new File(System.getProperty("20151164010106.Bibliotecas") + File.separator + "Documentos"+File.separator + "img");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir;
    }
    /*
    public void handleFileUpload(FileUploadEvent event) {

		byte[] file = new byte[event.getFile().getContents().length];
		System.arraycopy(event.getFile().getContents(),0,file,0,event.getFile().getContents().length);
		this.medico.setImagem(file);		
		
		upload();
    }
	*/
	
	

	public Medico getMedico(){
		return this.medico;
	}
	
	public void setMedico(Medico umMedico) {
		this.medico = umMedico;
	}
	
	public String salvarButao(Medico umMedico) {
		salvar(umMedico);				
		
		return "listaDeMedicos";
		
	}

	
	
	public Medico pesquisarMedicoPorId(long idMedico) {
		EntityManager em = JPAUtil.getEntityManager();
		Medico medico = null;
	    try {
	      //Consulta uma pessoa pelo seu ID.
	    	medico = em.find(Medico.class, idMedico);
	    } finally {
	    	em.close();
	    }
	    return medico;
	}
	


}
