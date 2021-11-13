package utcn.ti.proiect_licenta.JUnitTests;


import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONUtil;
import org.json.JSONString;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import utcn.ti.proiect_licenta.ProiectLicentaApplication;
import utcn.ti.proiect_licenta.config.WebSecurityConfig;
import utcn.ti.proiect_licenta.dto.BeneficiarDTO;
import utcn.ti.proiect_licenta.dto.ContractTertiDTO;
import utcn.ti.proiect_licenta.dto.LoginDTO;
import utcn.ti.proiect_licenta.dto.StatFunctiiAndAngajatDTO;
import utcn.ti.proiect_licenta.model.*;
import utcn.ti.proiect_licenta.repository.*;
import utcn.ti.proiect_licenta.service.UserService;
import org.junit.Test;

import javax.transaction.Transactional;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProiectLicentaApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Transactional
public class IntegrationTesting {

    @Autowired
    WebApplicationContext wac;
    private MockMvc mockMvc;

    @Autowired
    private BeneficiarRepository beneficiarRepository;
    @Autowired
    private ContractTertiRepository contractTertiRepository;
    @Autowired
    private AdresaRepository adresaRepository;
    @Autowired
    private CursValutarRepository cursValutarRepository;
    @Autowired
    private AngajatRepository angajatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartamentRepository departamentRepository;
    @Autowired
    private FacultateRepository facultateRepository;
    @Autowired
    private UniversitateRepository universitateRepository;
    @Autowired
    private BancaRepository bancaRepository;
    @Autowired
    private MembruRepository membruRepository;



    private Beneficiar beneficiar;
    private BeneficiarDTO beneficiarDTO;
    private ContractTerti contractTerti;
    private Adresa adresaBeneficiar, adresaUniversitate;
    private Banca bancaBeneficiar, bancaUniveristate;
    private CursValutar cursValutarAzi, cursValutarIeri;
    private User user, user2;
    private Angajat director, director2;
    private ContractTertiDTO contractTertiDTO;
    private Universitate universitate;
    private Facultate facultate;
    private Departament departament;
    private StatFunctii statFunctiiMocked, statFunctii;
    Set<StatFunctii> listaStatFunctiiMocked, listaStatFunctii;


    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .build();

        adresaUniversitate = new Adresa("Romania", "CJ", "Cluj-Napoca", "Baritiu", "26");
        bancaUniveristate = new Banca("BNR", "ROBNR2394628028294839");
        universitate = new Universitate("Universitatea Tehnica din Cluj-Napoca", "024525656", "024525655", "32542", "Rector S.G.", "Prorector M.G.",
                adresaUniversitate, bancaUniveristate);
        facultate = new Facultate("Calculatoare si Tehnologia Informatiei", "CTI", universitate);
        departament = new Departament("Calculatoare", "2_CALC", facultate);


        director = new Angajat("Director", departament);
        director2 = new Angajat("Director2", departament);
        user = new User("email@gmail.com", "parola", "director");
        user2 = new User("email2@gmail.com", "parola2", "director");

        beneficiar = new Beneficiar("Dedeman SRL", "1244", "02635124", "dedeman@gmail.com", "Maria Pop", "director");
        beneficiarDTO = new BeneficiarDTO("Dedeman SRL", "1244", "dedeman@hotmail.com", "Romania", "CJ", "Cluj-Napoca", "Garii", "123", "Pop F.", "director", "1244", "ROBTRL236527327", "BT");


        contractTerti = new ContractTerti(1, Date.valueOf("2020-12-13"), "CDI", "I.A.A.S", 40000.00, "EUR", Date.valueOf("2021-12-13"), 4, 4, 2,
                Date.valueOf("2020-12-13"), Date.valueOf("2021-12-13"), "Demian Pop", 4000.00);
        contractTerti.setBeneficiar(beneficiar);


        cursValutarAzi = new CursValutar("EUR", 4.86, java.sql.Date.valueOf(new Date(System.currentTimeMillis()).toString()));
        cursValutarIeri = new CursValutar("EUR", 4.84, new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L));

        bancaBeneficiar = new Banca("BT", "ROBTRL12314132473289");
        adresaBeneficiar = new Adresa("Romania", "BN", "Bistrita", "Principala", "120A");
        beneficiar.setAdresa(adresaBeneficiar);
        Set<Banca> banciBeneficiar = new HashSet();
        banciBeneficiar.add(bancaBeneficiar);
        beneficiar.setBanci(banciBeneficiar);



        Membru directorDMCDI = new Membru("G.I.M", "Director DMCDI");
        membruRepository.saveAndFlush(directorDMCDI);

        statFunctiiMocked = new StatFunctii("director", Date.valueOf("2020-12-13"), Date.valueOf("2021-12-13"));
        statFunctii = new StatFunctii("director", Date.valueOf("2020-08-13"), Date.valueOf("2021-08-13"));
        statFunctiiMocked.setContractTerti(contractTerti);
        statFunctiiMocked.setAngajat(director);
        listaStatFunctiiMocked = new HashSet<>();
        listaStatFunctiiMocked.add(statFunctiiMocked);

        contractTerti.setStatFunctiiList(listaStatFunctiiMocked);

        adresaRepository.saveAndFlush(adresaUniversitate);
        bancaRepository.saveAndFlush(bancaUniveristate);
        universitate.setCodUniversitate("UTCN");
        universitateRepository.saveAndFlush(universitate);
        facultateRepository.saveAndFlush(facultate);
        departamentRepository.saveAndFlush(departament);
        adresaRepository.saveAndFlush(adresaBeneficiar);
        bancaRepository.saveAndFlush(bancaBeneficiar);
        beneficiarRepository.saveAndFlush(beneficiar);


    }


    @WithMockUser(authorities = WebSecurityConfig.DIRECTOR_AUTHORITY)
    @Test
    public void addBeneficiarTest() throws Exception {

        mockMvc.perform(post("/beneficiar/add/")
                .content(JsonString(beneficiar))
                .contentType("application/json"))
                .andExpect(status().isOk());

    }

    @WithMockUser(authorities = WebSecurityConfig.DIRECTOR_AUTHORITY)
    @Test
    public void saveBeneficiarTest() throws Exception {

        //adaugare beneficiar nou
        mockMvc.perform(post("/beneficiar/saveBeneficiar/")
                .content(JsonString(beneficiarDTO))
                .contentType("application/json"))
                .andExpect(status().isOk());

        Beneficiar beneficiarFound = beneficiarRepository.findByCifCui("1244");
        Assert.assertEquals(beneficiarDTO.getCif(), beneficiarFound.getCifCui());

        //verificam daca se face update si nu se adauga un beneficiar nou
        beneficiarDTO.setJudet("BN");
        mockMvc.perform(post("/beneficiar/saveBeneficiar/")
                .content(JsonString(beneficiarDTO))
                .contentType("application/json"))
                .andExpect(status().isOk());
        Beneficiar beneficiarFound2 = beneficiarRepository.findByCifCui("1244");
        Assert.assertEquals(beneficiarDTO.getJudet(), beneficiarFound2.getAdresa().getJudet());
        Assert.assertEquals(beneficiarDTO.getCif(), beneficiarFound2.getCifCui());
    }


    @WithMockUser(authorities = WebSecurityConfig.DIRECTOR_AUTHORITY)
    @Test
    public void getBeneficiarByContractTest() throws Exception {
        adresaRepository.saveAndFlush(adresaBeneficiar);
        beneficiarRepository.saveAndFlush(beneficiar);
        contractTertiRepository.saveAndFlush(contractTerti);

        mockMvc.perform(get("/beneficiar/{idContract}/", contractTerti.getIdContractTerti()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cifCui", is("1244")));
    }

    @WithMockUser(authorities = WebSecurityConfig.DIRECTOR_AUTHORITY)
    @Test
    public void getByDenumireAndCifCuiTest() throws Exception {
        adresaRepository.saveAndFlush(adresaBeneficiar);
        beneficiarRepository.saveAndFlush(beneficiar);

        mockMvc.perform(get("/beneficiar/getByDenumireAndCifCui/{denumire}/{cifCui}/", beneficiar.getDenumire(), beneficiar.getCifCui()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cifCui", is("1244")));
    }

    @WithMockUser(authorities = WebSecurityConfig.DIRECTOR_AUTHORITY)
    @Test
    public void addContractTest() throws Exception {

        cursValutarRepository.saveAndFlush(cursValutarAzi);
        cursValutarRepository.saveAndFlush(cursValutarIeri);
        user2.setAngajat(director2);
        userRepository.saveAndFlush(user2);
        angajatRepository.saveAndFlush(director2);
        contractTertiDTO = new ContractTertiDTO("1244", director2.getIdAngajat(), "S.I", "Proiect Cercetare", "EUR", 40000.00, 36000.00, 4000.00, "CDI", 2153, Date.valueOf("2020-11-02"),
                Date.valueOf("2021-11-02"), Date.valueOf("2020-11-02"), Date.valueOf("2021-11-02"), 4, 2, 1, 1);

        mockMvc.perform(post("/contractTerti/add/")
                .content(JsonString(contractTertiDTO))
                .contentType("application/json"))
                .andExpect(status().isOk());

    }


    @WithMockUser(authorities = WebSecurityConfig.DIRECTOR_AUTHORITY)
    @Test
    public void getTitluProiecteTest() throws Exception {

        director.setFunctii(listaStatFunctiiMocked);
        angajatRepository.saveAndFlush(director);
        contractTertiRepository.saveAndFlush(contractTerti);

        mockMvc.perform(get("/cerereImprumut/titluProiecte/{idAngajat}/",director.getIdAngajat()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(hasSize(1))));
    }

    @WithMockUser(authorities = WebSecurityConfig.ADMIN_AUTHORITY)
    @Test
    public void addMembruTest() throws Exception {

        Membru membru = new Membru("Ana Tudor","Staff DMCDI");

        mockMvc.perform(post("/admin/addMembru/")
                .content(JsonString(membru))
                .contentType("application/json"))
                .andExpect(status().isOk());

    }
    @WithMockUser(authorities = WebSecurityConfig.ADMIN_AUTHORITY)
    @Test
    public void getAllDepartamenteTest() throws Exception {

        mockMvc.perform(get("/admin/allDepartamente/"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(hasSize(1))));
    }
    @WithMockUser(authorities = WebSecurityConfig.ADMIN_AUTHORITY)
    @Test
    public void changePasswordTest() throws Exception {
        User userN = new User("n@gmail.com","parola","director");
        Angajat angajat = new Angajat("Narcisa",departament);
        userN.setAngajat(angajat);
        userRepository.saveAndFlush(userN);
        mockMvc.perform(put("/user/changePassword/{idUser}/",userN.getIdUser())
                .content(JsonString("changePass"))
                .contentType("application/json"))
                .andExpect(status().isOk());

    }

    private String JsonString(Object obiect) {
        try {
            return new ObjectMapper().writeValueAsString(obiect);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
