candidat(1).
candidat(3). %x = 3
candidat(2).

genereaza(4,Col,Col,_):-!.
genereaza(Poz,Col,Rez,P):- Poz1 is Poz + 1,
                        candidat(X),
                        P1 is P*X,
                        P1 mod 27 =\= 0, 
                        genereaza(Poz1,[X|Col],Rez,P1).
        

        
                                
gen(Rez):-  genereaza(1,[1],Rez,1).
gen(Rez):-  genereaza(1,[3],Rez,3).
                        
pb(List) :-
        findall(Rez, gen(Rez), List).