package com.example.library.bootstrap;
/*
Author: BeGieU
Date: 05.02.2019
*/

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.Comment;
import com.example.library.services.AuthorService;
import com.example.library.services.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent>
{
    private final AuthorService authorService;
    private final BookService bookService;

    public DataLoader(AuthorService authorService, BookService bookService)
    {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    private void loadData()
    {
        Book book1 = new Book();
        book1.setName("Pan Tadeusz");
        book1.setDescription("Do rodzinnego domu przyjeżdża ze szkół w Wilnie na wakacje młody Tadeusz." +
                " Okazuje się, że Soplicowo, majątek jego opiekuna Sędziego Soplicy, jest pełne gości," +
                " którzy zjechali tu, aby uczestniczyć w procesie o stary zamek, niegdyś własność bogatego" +
                " magnata Stolnika Horeszki. Proces toczy się między ostatnim krewnym Horeszki, Hrabią i Sędzią Soplicą.\n");
        book1.setPublisher("Ciemna Strefa");

        Comment comment1=new Comment();
        comment1.setContent("Zajbista fchuj mordeczko polecam");
        comment1.setUser("DobryChlopak321");
        comment1.setWhenAdded(LocalDate.now());
        comment1.addBook(book1);

        Comment comment2=new Comment();
        comment2.setContent("Essa dobre");
        comment2.setUser("sssw4");
        comment2.setWhenAdded(LocalDate.now());
        comment2.addBook(book1);



        Book book2=new Book();
        book2.setName("Slepnac od swiatel");
        book2.setDescription("Zawsze chodzi wyłącznie o pieniądze. O nic innego. Ktoś może powiedzieć ci," +
                " że to niska pobudka. To nieprawda - oświadcza bohater powieści Jakuba Żulczyka. Ten młody" +
                " człowiek przyjechał z Olsztyna do Warszawy, gdzie prawie skończył ASP. By uniknąć powielania" +
                " egzystencjalnych schematów swoich rówieśników – przyszłych meneli, ludzi mogących w najlepszym" +
                " razie otrzeć się o warstwy klasy średniej, niepoprawnych idealistów – dokonał życiowego wybor" +
                "u według własnych upodobań: Zawsze lubiłem ważyć i liczyć.");
        book2.setPublisher("Step Rec");

        Comment comment3=new Comment();
        comment3.setContent("O KURWA ALE to jest dobre");
        comment3.setUser("byqu");
        comment3.setWhenAdded(LocalDate.now());
        comment3.addBook(book2);

        Author author1=new Author();
        author1.setFirstName("Jakub");
        author1.setLastName("Zulczyk");
        author1.setDesctiption("Popularny pisarz młodego pokolenia. Urodzony w Szczytnie, ukończył studia dziennikarskie" +
                "na UJ. Współpracownik pism „Lampa” i „Machina”, twórca rubryki „Tydzień kultury polskiej” w tygodniku" +
                " „Wprost”. Autor czterech powieści, z których dwie ostatnie – \"Instytut\" i \"Zmorojewo\" wpisują się" +
                " w konwencję horroru (przy czym \"Zmorojewo\" jest przede wszystkim powieścią przygodową dla młodszych" +
                " czytelników). Fani alternatywnej muzyki rockowej z kolei wysoko cenią sobie Radio Armageddon, wydane w 2008." +
                " Sam Jakub nazywa siebie „pisarzem, niezależnym publicystą, recenzentem, felietonistą, blogerem, konsumentem" +
                " śmieci i wzorowym odbiorcą kultury masowej”.");

        Author author2=new Author();
        author2.setFirstName("Adam");
        author2.setLastName("Mickiewicz");
        author2.setDesctiption("Polski poeta, działacz i publicysta polityczny. Obok Juliusza Słowackiego i Zygmunta Krasińskiego" +
                " uważany za największego poetę polskiego romantyzmu (grono tzw. Trzech Wieszczów) oraz literatury polskiej w " +
                "ogóle, a nawet za jednego z największych na skalę europejską. Określany też przez innych jako poeta przeobrażeń" +
                " oraz bard słowiański.");


        book1.addAuthor(author2);
        book1.addAuthor(author1);
        book2.addAuthor(author1);

        authorService.save(author1);
        authorService.save(author2);

        bookService.save(book1);
        bookService.save(book2);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        loadData();
    }
}
