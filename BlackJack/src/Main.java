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
        System.out.println("Ne kadarlık bahis oynamak istiyorsunuz ? (Bakiyeniz= "+balance+")");

        //boolean checkBet = input.hasNextInt();//Girilen değerin her basamağındaki ascii kodlarına bak sayıysa al diğer türlü hata verdir!

        while(true)
        {

            if(input.hasNextInt())
            {

                currentBet=input.nextInt();
                if(currentBet>0&&currentBet<=balance)
                {   clearScreen();
                    //System.out.println("Güncel Bet ="+ currentBet);
                    break;
                }
                else
                {
                    System.out.println("Lütfen Geçerli Bir Değer Giriniz!");
                    System.out.println("Ne kadarlık bahis oynamak istiyorsunuz ? (Bakiyeniz= "+balance+")");
                }
            }
            else
                {
                    System.out.println("Lütfen Geçerli Bir Değer Giriniz!");
                    System.out.println("Ne kadarlık bahis oynamak istiyorsunuz ? (Bakiyeniz= "+balance+")");
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

        while (true)//oyuncu puanı 21i geçmediği sürece kart çekmesine izin veriyor
        {
            sumPoints();
            if (playersSum>=21)
                break;

            System.out.print("\nKart Çekmek İstiyor Musunuz ?(e/h)");

            String control = input.next();

            if (control.charAt(0)=='e'&& control.length()==1)
            {
                player.add(drawACard());
                clearScreen();//burada terminal ekranını temizlememiz gerekiyor araştır!
                duringGameCards();

            }
            else if (control.charAt(0)=='h'&& control.length()==1)
                break;
            else
            {
                clearScreen();
                duringGameCards();
                System.out.println("\n---Lütfen Geçerli Bir Karakten Giriniz!---");
            }
        }
    }
    public static void duringGameCards()
    {
        System.out.println("Güncel Bet="+currentBet);
        System.out.print("Kurpiyerin Kartları= " + casino.get(0) + " KAPALI\n");
        System.out.println("\n\n\n");
        System.out.print("Oyuncunun Kartları= ");
        for (String a : player) System.out.print(a + "   ");//kartı çektikten sonra ekrana yazdırma
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
            System.out.println("Güncel Bakiyeniz= "+balance);
        }

        else if (casinoSum>21)
        {
            System.out.println("\n---------Kazandınız!---------");
            balance+=currentBet;
            System.out.println("Güncel Bakiyeniz= "+balance);
        }

        else if (playersSum>casinoSum)
        {
            System.out.println("\n---------Kazandınız!---------");
            balance+=currentBet;
            System.out.println("Güncel Bakiyeniz= "+balance);
        }

        else if (playersSum==casinoSum)
        {
            System.out.println("\n---------Beraberlik---------");
            System.out.println("Güncel Bakiyeniz= "+balance);
        }

        else
        {
            System.out.println("\n---------Kaybettiniz---------");
            balance-=currentBet;
            System.out.println("Güncel Bakiyeniz= "+balance);
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
            System.out.println("---------------BlackJack Oyununa Hoşgeldiniz---------------");
            System.out.println("Yeni Oyun için 1'e basınız");
            System.out.println("Oyun Kuralları için 2'ye basınız");
            System.out.println("Hakkımızda kısmı için 3'e basınız");
            System.out.println("Çıkmak için 4'e basınız");
            String starter = input.next();
            if(starter.equals("1"))//Oyunun başladığı yer
            {
                startANewGame=true;//oyuncu hayır dediği zaman bunu false yapıyor menüye döndükten sonra tekrar oynamak isterse diye
                while(startANewGame)
                {
                    if(balance==0)//bakiyesi olmadığı halde yeni oyun oynamak istiyorsa girmiyor!
                    {
                        clearScreen();
                        System.out.println("---Bakiyeniz Olmadığı için yeni oyun oynayamazsınız!---\n");
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

                    System.out.print("\nKurpiyerin Kartları= ");
                    for (String a : casino) System.out.print(a + "   ");
                    System.out.println("\n\n\n");
                    System.out.print("Oyuncunun Kartları= ");
                    for (String a : player) System.out.print(a + "   ");

                    winner();


                    while(true)//ilk oyundan sonra yeni oyun durumunu kontrol ediyor
                    {
                        System.out.println("Yeni Oyun Oynamak İstyitor Musunuz ?(e/h)");
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

                        else System.out.println("Yanlış Karakter Girildi!");

                    }
                }
            }

            else if (starter.equals("2"))//Oyun kuralları kısmı
            {
                clearScreen();
                BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\doguk\\IdeaProjects\\BlackJack\\nasilOynanir.txt"));

                //line="";
                while ((line= br.readLine())!=null)
                {
                    System.out.println(line);
                }
                System.out.println("Geri Gelmek için 1'e basınız");
                while(true)//geri gelmek için 1 koşulunu istiyor
                {
                    if (input.hasNextInt())
                    {
                        int a = input.nextInt();
                        if(a==1)
                        {
                            clearScreen();
                            break;
                        }
                        else System.out.println("Geri Gelmek için 1'e basınız");
                    }
                    else
                    {
                        System.out.println("Geri Gelmek için 1'e basınız");
                        input.next();
                    }

                }

            }

            else if (starter.equals("3"))//hakkımızda kısmı
            {
                clearScreen();
                BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\doguk\\IdeaProjects\\BlackJack\\nasilOynanir.txt"));

                //line="";
                while ((line= br.readLine())!=null)
                {
                    System.out.println(line);
                }
                System.out.println("Geri Gelmek için 1'e basınız");
                while(true)//geri gelmek için 1 koşulunu istiyor
                {
                    if (input.hasNextInt())
                    {
                        int a = input.nextInt();
                        if(a==1)
                        {
                            clearScreen();
                            break;
                        }
                        else System.out.println("Geri Gelmek için 1'e basınız");
                    }
                    else
                    {
                        System.out.println("Geri Gelmek için 1'e basınız");
                        input.next();
                    }

                }
            }

            else if (starter.equals("4"))//çıkış
                System.exit(1);
            else
            {
                clearScreen();
                System.out.println("Lütfen Geçerli Bir Değer Giriniz!");
            }

        }
    }

}
