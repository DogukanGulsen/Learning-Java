import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main {
    public static Random rnd = new Random();
    public static ArrayList <String> casino= new ArrayList<String>();
    public static ArrayList <String> player=new ArrayList<String>();
    public static ArrayList <String> Deck=new ArrayList<String>();
    public static int playersSum=0,casinoSum=0;
    public static Scanner input =new Scanner(System.in);
    public static int balance=100,currentBet=0;


    public static String drawACard()
    {
        int cardIndex = rnd.nextInt(Deck.size());
        String card = Deck.get(cardIndex);
        Deck.remove(cardIndex);
        return card;
    }
    public static void createDeck()
    {
        Deck.add("Sinek-As");
        Deck.add("Sinek-2");
        Deck.add("Sinek-3");
        Deck.add("Sinek-4");
        Deck.add("Sinek-5");
        Deck.add("Sinek-6");
        Deck.add("Sinek-7");
        Deck.add("Sinek-8");
        Deck.add("Sinek-9");
        Deck.add("Sinek-10");
        Deck.add("Sinek-Joker");
        Deck.add("Sinek-Queen");
        Deck.add("Sinek-King");
        Deck.add("Karo-As");
        Deck.add("Karo-2");
        Deck.add("Karo-3");
        Deck.add("Karo-4");
        Deck.add("Karo-5");
        Deck.add("Karo-6");
        Deck.add("Karo-7");
        Deck.add("Karo-8");
        Deck.add("Karo-9");
        Deck.add("Karo-10");
        Deck.add("Karo-Joker");
        Deck.add("Karo-Queen");
        Deck.add("Karo-King");
        Deck.add("Kupa-As");
        Deck.add("Kupa-2");
        Deck.add("Kupa-3");
        Deck.add("Kupa-4");
        Deck.add("Kupa-5");
        Deck.add("Kupa-6");
        Deck.add("Kupa-7");
        Deck.add("Kupa-8");
        Deck.add("Kupa-9");
        Deck.add("Kupa-10");
        Deck.add("Kupa-Joker");
        Deck.add("Kupa-Queen");
        Deck.add("Kupa-King");
        Deck.add("Maca-As");
        Deck.add("Maca-2");
        Deck.add("Maca-3");
        Deck.add("Maca-4");
        Deck.add("Maca-5");
        Deck.add("Maca-6");
        Deck.add("Maca-7");
        Deck.add("Maca-8");
        Deck.add("Maca-9");
        Deck.add("Maca-10");
        Deck.add("Maca-Joker");
        Deck.add("Maca-Queen");
        Deck.add("Maca-King");
    }
    public static void gameStart()
    {
        System.out.println("Ne kadarl??k bahis oynamak istiyorsunuz ? (Bakiyeniz= "+balance+")");

        //boolean checkBet = input.hasNextInt();//Girilen de??erin her basama????ndaki ascii kodlar??na bak say??ysa al di??er t??rl?? hata verdir!

        while(true)
        {

            if(input.hasNextInt())
            {

                currentBet=input.nextInt();
                if(currentBet>0&&currentBet<=balance)
                {   clearScreen();
                    //System.out.println("G??ncel Bet ="+ currentBet);
                    break;
                }
                else
                {
                    System.out.println("L??tfen Ge??erli Bir De??er Giriniz!");
                    System.out.println("Ne kadarl??k bahis oynamak istiyorsunuz ? (Bakiyeniz= "+balance+")");
                }
            }
            else
                {
                    System.out.println("L??tfen Ge??erli Bir De??er Giriniz!");
                    System.out.println("Ne kadarl??k bahis oynamak istiyorsunuz ? (Bakiyeniz= "+balance+")");
                    input.next();
                }

        }
        for (int i=0;i<4;i++)
        {
            if(i%2==0) player.add(drawACard());
            else casino.add(drawACard());
        }
    }
    public static void sumPoints()
    {
        playersSum=0;
        casinoSum=0;
        for (String s : casino)
        {
            if (s.charAt(s.length() - 1) == 's')
                casinoSum += 11;
            else if (s.charAt(s.length() - 1) == 'r' || s.charAt(s.length() - 1) == 'n' || s.charAt(s.length() - 1) == 'g'|| s.charAt(s.length() - 1) == '0')
                casinoSum += 10;
            else casinoSum += Integer.parseInt(s.substring(s.length() - 1));
        }
        for (String s : casino)
        {
            if (s.charAt(s.length() - 1) == 's' && casinoSum > 21)
                casinoSum -= 10;
        }

        for (String s : player)
        {
            if (s.charAt(s.length() - 1) == 's') {
                playersSum += 11;
            } else if (s.charAt(s.length() - 1) == 'r' || s.charAt(s.length() - 1) == 'n' || s.charAt(s.length() - 1) == 'g'|| s.charAt(s.length() - 1) == '0') {
                playersSum += 10;
            } else playersSum += Integer.parseInt(s.substring(s.length() - 1));
        }
        for (String s : player)
        {
            if (s.charAt(s.length() - 1) == 's' && playersSum > 21)
                playersSum -= 10;
        }

    }
    public static void playerDuringGame()
    {

        while (true)//oyuncu puan?? 21i ge??medi??i s??rece kart ??ekmesine izin veriyor
        {
            sumPoints();
            if (playersSum>=21)
                break;

            System.out.print("\nKart ??ekmek ??stiyor Musunuz ?(e/h)");

            String control = input.next();

            if (control.charAt(0)=='e'&& control.length()==1)
            {
                player.add(drawACard());
                clearScreen();//burada terminal ekran??n?? temizlememiz gerekiyor ara??t??r!
                duringGameCards();

            }
            else if (control.charAt(0)=='h'&& control.length()==1)
                break;
            else
            {
                clearScreen();
                duringGameCards();
                System.out.println("\n---L??tfen Ge??erli Bir Karakten Giriniz!---");
            }
        }
    }
    public static void duringGameCards()
    {
        System.out.println("G??ncel Bet="+currentBet);
        System.out.print("Kurpiyerin Kartlar??= " + casino.get(0) + " KAPALI\n");
        System.out.println("\n\n\n");
        System.out.print("Oyuncunun Kartlar??= ");
        for (String a : player) System.out.print(a + "   ");//kart?? ??ektikten sonra ekrana yazd??rma
    }
    public static void casinoDuringGame()
    {

        while(casinoSum<17)
        {
            casino.add(drawACard());
            sumPoints();
        }

    }
    public static void winner()
    {
        if (playersSum>21)
        {
            System.out.println("\n---------Kaybettiniz---------");
            balance-=currentBet;
            System.out.println("G??ncel Bakiyeniz= "+balance);
        }

        else if (casinoSum>21)
        {
            System.out.println("\n---------Kazand??n??z!---------");
            balance+=currentBet;
            System.out.println("G??ncel Bakiyeniz= "+balance);
        }

        else if (playersSum>casinoSum)
        {
            System.out.println("\n---------Kazand??n??z!---------");
            balance+=currentBet;
            System.out.println("G??ncel Bakiyeniz= "+balance);
        }

        else if (playersSum==casinoSum)
        {
            System.out.println("\n---------Beraberlik---------");
            System.out.println("G??ncel Bakiyeniz= "+balance);
        }

        else
        {
            System.out.println("\n---------Kaybettiniz---------");
            balance-=currentBet;
            System.out.println("G??ncel Bakiyeniz= "+balance);
        }
    }
    public static void clearScreen()
    {
        for(int i = 0; i < 50; i++)
            System.out.print("\n");
    }
    public static void main(String[] args) throws IOException
    {
        String line = "";
        createDeck();
        boolean startANewGame=true;
        while(true)
        {
            System.out.println("---------------BlackJack Oyununa Ho??geldiniz---------------");
            System.out.println("Yeni Oyun i??in 1'e bas??n??z");
            System.out.println("Oyun Kurallar?? i??in 2'ye bas??n??z");
            System.out.println("Hakk??m??zda k??sm?? i??in 3'e bas??n??z");
            System.out.println("????kmak i??in 4'e bas??n??z");
            String starter = input.next();
            if(starter.equals("1"))//Oyunun ba??lad?????? yer
            {
                startANewGame=true;//oyuncu hay??r dedi??i zaman bunu false yap??yor men??ye d??nd??kten sonra tekrar oynamak isterse diye
                while(startANewGame)
                {
                    if(balance==0)//bakiyesi olmad?????? halde yeni oyun oynamak istiyorsa girmiyor!
                    {
                        clearScreen();
                        System.out.println("---Bakiyeniz Olmad?????? i??in yeni oyun oynayamazs??n??z!---\n");
                        break;
                    }
                    player.clear();
                    casino.clear();
                    gameStart();
                    sumPoints();
                    duringGameCards();

                    playerDuringGame();
                    casinoDuringGame();
                    clearScreen();

                    System.out.print("\nKurpiyerin Kartlar??= ");
                    for (String a : casino) System.out.print(a + "   ");
                    System.out.println("\n\n\n");
                    System.out.print("Oyuncunun Kartlar??= ");
                    for (String a : player) System.out.print(a + "   ");

                    winner();


                    while(true)//ilk oyundan sonra yeni oyun durumunu kontrol ediyor
                    {
                        System.out.println("Yeni Oyun Oynamak ??styitor Musunuz ?(e/h)");
                        String newGame = input.next();

                        if(newGame.equals("e"))
                        {
                            if(Deck.size()<=26)
                            {
                                Deck.clear();
                                createDeck();
                            }
                            break;
                        }
                        else if (newGame.equals("h"))
                        {
                            clearScreen();
                            startANewGame=false;
                            break;
                        }

                        else System.out.println("Yanl???? Karakter Girildi!");

                    }
                }
            }

            else if (starter.equals("2"))//Oyun kurallar?? k??sm??
            {
                clearScreen();
                BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\doguk\\IdeaProjects\\BlackJack\\nasilOynanir.txt"));

                //line="";
                while ((line= br.readLine())!=null)
                {
                    System.out.println(line);
                }
                System.out.println("Geri Gelmek i??in 1'e bas??n??z");
                while(true)//geri gelmek i??in 1 ko??ulunu istiyor
                {
                    if (input.hasNextInt())
                    {
                        int a = input.nextInt();
                        if(a==1)
                        {
                            clearScreen();
                            break;
                        }
                        else System.out.println("Geri Gelmek i??in 1'e bas??n??z");
                    }
                    else
                    {
                        System.out.println("Geri Gelmek i??in 1'e bas??n??z");
                        input.next();
                    }

                }

            }

            else if (starter.equals("3"))//hakk??m??zda k??sm??
            {
                clearScreen();
                BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\doguk\\IdeaProjects\\BlackJack\\nasilOynanir.txt"));

                //line="";
                while ((line= br.readLine())!=null)
                {
                    System.out.println(line);
                }
                System.out.println("Geri Gelmek i??in 1'e bas??n??z");
                while(true)//geri gelmek i??in 1 ko??ulunu istiyor
                {
                    if (input.hasNextInt())
                    {
                        int a = input.nextInt();
                        if(a==1)
                        {
                            clearScreen();
                            break;
                        }
                        else System.out.println("Geri Gelmek i??in 1'e bas??n??z");
                    }
                    else
                    {
                        System.out.println("Geri Gelmek i??in 1'e bas??n??z");
                        input.next();
                    }

                }
            }

            else if (starter.equals("4"))//????k????
                System.exit(1);
            else
            {
                clearScreen();
                System.out.println("L??tfen Ge??erli Bir De??er Giriniz!");
            }

        }
    }

}
