package utcn.ti.proiect_licenta.JUnitTests;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import utcn.ti.proiect_licenta.ProiectLicentaApplication;
import utcn.ti.proiect_licenta.dto.*;
import utcn.ti.proiect_licenta.model.*;
import utcn.ti.proiect_licenta.repository.*;
import utcn.ti.proiect_licenta.service.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProiectLicentaApplication.class)
public class UnitTesting {


    @Autowired
    private AngajatService angajatService;
    @Autowired
    private UserService userService;
    @Autowired
    private CheltuialaService cheltuialaService;
    @Autowired
    private ContractTertiService contractTertiService;
    @Autowired
    private IncasareService incasareService;
    @Autowired
    private CursValutarService cursValutarService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private AngajatRepository angajatRepository;
    @MockBean
    private ContractTertiRepository contractTertiRepository;
    @MockBean
    private CheltuialaRepository cheltuialaRepository;

    private Cheltuiala cheltuialaMocked;
    private StatFunctii statFunctiiMocked;
    private ContractTerti contractTertiMocked;
    private Angajat angajatMocked;
    private ActAditional actAditionalMocked;
    private Adresa adresaUniversitateMocked, adresaBeneficiarMocked;
    private Banca  bancaUniveristate;
    private Beneficiar beneficiar;
    private Departament departamentMocked;
    private Facultate facultateMocked;
    private Universitate universitateMocked;
    private User userMocked;
    private ContractTerti contractTertiMocked2;
    private CerereImprumut cerereImprumutMocked;
    private Incasare incasareMocked;

    private Set<Cheltuiala> cheltuialaList = new HashSet<>();
    private List<CerereImprumut> cerereImprumutList = new ArrayList<>();
    private List<Incasare> incasareList = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        adresaUniversitateMocked = new Adresa("Romania", "CJ", "Cluj-Napoca", "Baritiu", "26");
        bancaUniveristate = new Banca("BNR", "ROBNR2394628028294839");
        universitateMocked = new Universitate("Universitatea Tehnica din Cluj-Napoca", "024525656", "024525655", "32542", "Rector S.G.", "Prorector M.G.",
                adresaUniversitateMocked, bancaUniveristate);
        universitateMocked.setIdUniversitate(1);
        facultateMocked = new Facultate("Calculatoare si Tehnologia Informatiei", "CTI", universitateMocked);
        facultateMocked.setIdFacultate(1);
        departamentMocked = new Departament("Calculatoare", "2_CALC", facultateMocked);

        angajatMocked = new Angajat("Galan Maria", departamentMocked);
        angajatMocked.setIdAngajat(1);

        actAditionalMocked = new ActAditional(1, Date.valueOf("2020-12-13"), Date.valueOf("2020-12-13")
                , Date.valueOf("2020-12-13"));
        actAditionalMocked.setIdActAditional(1);

        adresaBeneficiarMocked = new Adresa("Romania", "BN", "Bistrita", "Principala", "120A");

        beneficiar = new Beneficiar("Dedeman SRL", "1244", "02635124", "dedeman@gmail.com", "Maria Pop", "director");

        contractTertiMocked = new ContractTerti(1, Date.valueOf("2020-12-13"), "CDI", "I.A.A.S", 40000.00, "EUR", Date.valueOf("2021-12-13"), 4, 4, 2,
                Date.valueOf("2020-12-13"), Date.valueOf("2021-12-13"), "Demian Pop", 4000.00);
        contractTertiMocked.setIdContractTerti(1);
        contractTertiMocked.setBeneficiar(beneficiar);
        Set<ActAditional> listaActAditionalMocked = new HashSet<>();
        listaActAditionalMocked.add(actAditionalMocked);
        contractTertiMocked.setActAditionalLista(listaActAditionalMocked);

        statFunctiiMocked = new StatFunctii("director", Date.valueOf("2020-12-13"), Date.valueOf("2021-12-13"));
        statFunctiiMocked.setContractTerti(contractTertiMocked);
        statFunctiiMocked.setAngajat(angajatMocked);


        Set<StatFunctii> listaStatFunctiiMocked = new HashSet<>();
        listaStatFunctiiMocked.add(statFunctiiMocked);
        contractTertiMocked.setStatFunctiiList(listaStatFunctiiMocked);
        angajatMocked.setFunctii(listaStatFunctiiMocked);
        userMocked = new User("email@gmail.com", "password", "director");
        userMocked.setAngajat(angajatMocked);


        cheltuialaMocked = new Cheltuiala("251", Date.valueOf("2021-02-13"), "factura", 600.00, 60, "EUR", "none",
                "achizitie", 2898.0);
        cheltuialaMocked.setIdCheltuiala(1);
        cheltuialaMocked.setContractTerti(contractTertiMocked);

        cheltuialaList.add(cheltuialaMocked);
        contractTertiMocked.setCheltuialaLista(cheltuialaList);
        contractTertiMocked2 = new ContractTerti();
        contractTertiMocked2.setIdContractTerti(2);

        cerereImprumutMocked = new CerereImprumut(1, Date.valueOf("2021-02-13"), 2021, "-", 600.00, 0.0, 600.00, 0.0, 0.0, Date.valueOf("2021-04-13"), true);

        incasareMocked = new Incasare("1", Date.valueOf("2021-04-13"), "factura", 10000.00, 1000, "EUR", "-",
                "incasare");
        incasareMocked.setValoareInLei(48000.00);

    }

    @Test
    public void getUserByEmailTest() {

        User userMocked = new User("email@yahoo.com", "password", "director", angajatMocked);

        Mockito.when(userRepository.findByEmail(userMocked.getEmail()))
                .thenReturn(userMocked);

        UserDTO user = userService.findByEmail(userMocked.getEmail());

        Assert.assertEquals(user.getEmail(), userMocked.getEmail());
        Assert.assertEquals("director", user.getRole());
        Assert.assertEquals(angajatMocked.getIdAngajat(), user.getIdAngajat());
    }

    @Test
    public void saveUserTest() {
        User newUser = new User("narcisa@gmail.com", "narcisa", "director");
        Mockito.when(userRepository.save(any(User.class))).thenReturn(newUser);
        User saved = userService.save(newUser);

        Assert.assertEquals(newUser.getEmail(), saved.getEmail());
        Assert.assertEquals(newUser.getRole(), saved.getRole());
    }

    @Test
    public void changePasswordTest() {
        User user = new User("narcisa@gmail.com", "narcisa", "director");
        Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);
        User saved = userService.update(user, "Narcisa1@");

        Assert.assertEquals(user.getEmail(), saved.getEmail());
        Assert.assertEquals(user.getRole(), saved.getRole());
        Assert.assertTrue(BCRYPT_PATTERN.matcher(user.getPassword()).matches());

    }

    @Test
    public void getInfoAngajatByIdTest() {
        Mockito.when(angajatRepository.findById(1))
                .thenReturn(java.util.Optional.ofNullable(angajatMocked));
        AngajatInfoDTO angajatInfoDTO = angajatService.getInfoAngajatById(1);

        Assert.assertEquals(angajatMocked.getNume(), angajatInfoDTO.getNumeAngajat());
        Assert.assertEquals(angajatMocked.getDepartament(), angajatInfoDTO.getDepartament());
        Assert.assertEquals(angajatMocked.getDepartament().getFacultate(), angajatInfoDTO.getFacultate());
        Assert.assertEquals(angajatMocked.getDepartament().getFacultate().getUniversitate(), angajatInfoDTO.getUniversitate());
    }

    @Test
    public void findByContractDirectorContainingTest() {
        Mockito.when(userRepository.findById(userMocked.getIdUser()))
                .thenReturn(java.util.Optional.ofNullable(userMocked));

        List<AngajatDTO> listaAngajati;
        listaAngajati = angajatService.findByContractDirectorContaining(userMocked.getIdUser(), "Gal");
        Assert.assertEquals(angajatMocked.getNume(), listaAngajati.get(0).getNume());
        //cand nu exista un angajat care sa contina literele in nume
        listaAngajati = angajatService.findByContractDirectorContaining(userMocked.getIdUser(), "Flor");
        Assert.assertTrue(listaAngajati.isEmpty());
    }

    @Test
    public void getCheltuieliTableByContractTertiTest() {
        //testare cand toate datele sunt valide
        Mockito.when(contractTertiRepository.findById(contractTertiMocked.getIdContractTerti()))
                .thenReturn(java.util.Optional.ofNullable(contractTertiMocked));
        Mockito.when(cheltuialaRepository.findAllByContractTerti(contractTertiMocked))
                .thenReturn(new ArrayList<>(cheltuialaList));

        List<CheltuialaTable> lista = cheltuialaService.getCheltuieliTableByContractTerti(contractTertiMocked.getIdContractTerti());
        Assert.assertEquals(cheltuialaMocked.getNrDocument(), lista.get(0).getNrDocument());
    }

    @Test
    public void getCheltuieliTableByContractTertiTest2() {
        //testare cand contractul nu are cheltuieli
        Mockito.when(contractTertiRepository.findById(contractTertiMocked2.getIdContractTerti()))
                .thenReturn(java.util.Optional.ofNullable(contractTertiMocked2));
        Mockito.when(cheltuialaRepository.findAllByContractTerti(contractTertiMocked2))
                .thenReturn(null);
        List<CheltuialaTable> lista = cheltuialaService.getCheltuieliTableByContractTerti(contractTertiMocked2.getIdContractTerti());
        Assert.assertEquals("-", lista.get(0).getNrDocument());
    }

    @Test
    public void getCheltuieliTableByContractTertiTest3() {
        //testare cand contractul este invalid, adica nu a fost selectat din interfata
        List<CheltuialaTable> lista = cheltuialaService.getCheltuieliTableByContractTerti(0);
        Assert.assertEquals("-", lista.get(0).getNrDocument());
    }


    @Test
    public void findTitluProiectByAngajatTest() {
        Mockito.when(angajatRepository.findById(angajatMocked.getIdAngajat()))
                .thenReturn(java.util.Optional.ofNullable(angajatMocked));
        List<String> titluriProiecte = contractTertiService.findTitluProiectByAngajat(angajatMocked.getIdAngajat());

        Assert.assertEquals(contractTertiMocked.getTitluProiect(), titluriProiecte.get(0));
    }

    @Test
    public void valoareImprumutataTest() {

        //daca cererea este aprobata
        cerereImprumutList.add(cerereImprumutMocked);
        Double valoareImprumutata = contractTertiService.valoareImprumutata(cerereImprumutList);
        Assert.assertEquals(cerereImprumutMocked.getValoareaTotala(), valoareImprumutata);

        //daca lista e goala
        cerereImprumutList.remove(cerereImprumutMocked);
        valoareImprumutata = contractTertiService.valoareImprumutata(cerereImprumutList);
        Assert.assertEquals((Double) 0.0, valoareImprumutata);

        //daca cererea nu este aprobata
        cerereImprumutMocked.setAprobata(false);
        cerereImprumutList.add(cerereImprumutMocked);
        valoareImprumutata = contractTertiService.valoareImprumutata(cerereImprumutList);
        Assert.assertEquals((Double) 0.0, valoareImprumutata);

    }

    @Test
    public void valoareIncasataTest() {
        //cand lista contine incasari
        incasareList.add(incasareMocked);
        Double valoareaIncasata = contractTertiService.valoareIncasata(incasareList);
        Assert.assertEquals((Double) 48000.0, valoareaIncasata);

        //cand nu exista incasari
        incasareList.remove(incasareMocked);
        valoareaIncasata = contractTertiService.valoareIncasata(incasareList);
        Assert.assertEquals((Double)0.0,valoareaIncasata);
    }

    @Test
    public void valoareaCheltuitaTest(){
        //cand lista nu e goala
        Double valoareCheltuita = contractTertiService.valoareCheltuita(new ArrayList<>(cheltuialaList));
        Assert.assertEquals((Double)2898.0,valoareCheltuita);
        //cand lista e goala
        valoareCheltuita = contractTertiService.valoareCheltuita(new ArrayList<>());
        Assert.assertEquals((Double)0.0,valoareCheltuita);
    }

    @Test
    public void findAllIncasariByContractTest(){
        incasareList.clear();
        incasareList.add(incasareMocked);
        Set<Incasare> incasari = new HashSet<>(incasareList);
        contractTertiMocked.setIncasareLista(incasari);

        Mockito.when(contractTertiRepository.findById(contractTertiMocked.getIdContractTerti()))
                .thenReturn(java.util.Optional.ofNullable(contractTertiMocked));

        List<Incasare> incasariContract = incasareService.findAllByContract(contractTertiMocked.getIdContractTerti());
        Assert.assertEquals(incasareMocked.getNrDocument(),incasariContract.get(0).getNrDocument());

    }

    @Test
    public void dataCitireCursValutarTest(){
        //apelam cu data dintr-o zi normala din cursul saptamanii,marti, dupa ora 13
        Date data = cursValutarService.dataCitireCursValutar(Date.valueOf("2021-01-12"),true);
        //data trebuie sa fie aceeasi, neschimbata
        Assert.assertEquals(Date.valueOf("2021-01-12"),data);

        //apelam intr-o zi obisnuita inainte de ora 13
        Date inainteDe13 = cursValutarService.dataCitireCursValutar(Date.valueOf("2021-01-12"),false);
        Date expected=Date.valueOf(new Date(System.currentTimeMillis()-24*60*60*1000).toString());
        //trebuie sa fie data de ieri deoarece nu este curs inainte de ora 13 in ziua curenta
        //ieri inseamna in cazul nostru data curenta - 1. nu o sa fie data de 11-01-2021
        Assert.assertEquals(expected,inainteDe13);
    }
    @Test
    public void dataCitireCursValutarTest1(){
        //apelam intr-o zi de sambata, nu mai conteaza daca e dupa sau inainte de ora 13
        Date data = cursValutarService.dataCitireCursValutar(Date.valueOf("2021-01-16"),true);
        Date expected=Date.valueOf(new Date(System.currentTimeMillis()-24*60*60*1000).toString());
        //trebuie sa fie data de vineri, adica de ieri, in weekend nu avem curs
        Assert.assertEquals(expected,data);

        //apelam intr-o zi de duminica
        Date data2 = cursValutarService.dataCitireCursValutar(Date.valueOf("2021-01-17"),true);
        Date expected2=Date.valueOf(new Date(System.currentTimeMillis()-48*60*60*1000).toString());
        //cursul de vineri, 2 zile in urma
        Assert.assertEquals(expected2,data2);
    }
    @Test
    public void dataCitireCursValutar2(){
        //apelam intr-o zi de luni inainte de ora 13, nu o sa avem curs
        Date data = cursValutarService.dataCitireCursValutar(Date.valueOf("2021-01-18"),false);
        Date expected=Date.valueOf(new Date(System.currentTimeMillis()-72*60*60*1000).toString());
        //cursul de vineri, 3 zile in urma
        Assert.assertEquals(expected,data);
    }


}
