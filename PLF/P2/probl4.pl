%se_afla(E: integer, L: list)
%se_afla(i,i)
%E - elementul pe care il cautam in lista
%L - lista in care cautam elementul E
se_afla(E,[E|_]):-!.
se_afla(E,[_|T]):-se_afla(E,T).

%adauga_pozitie(E: integer, L:list, LRez:list)
%adauga_pozitie(i,i,o)
%E - elementul pe care vrem sa il adaugam pe pozitia corespunzatoare in lista L
%L - lista in care vrem sa inseram elementul E
%LRez - lista rezultata din adaugarea elementului E in lista L`` 
adauga_pozitie(E,[],[E]):-!.
adauga_pozitie(E,[H|T],[E|[H|T]]):-
    H > E,
    !.
adauga_pozitie(E,[H|T],[H|Rez]):-
    adauga_pozitie(E,T,Rez).

%elimina_duplicate(L:list, LRez:list)
%elimina_duplicate(i,o)
%L - lista sortata din care vrem sa stergem duplicatele
%LRez - lista rezulatata in urma stergerii duplicatelor
elimina_duplicate([],[]).
elimina_duplicate([H1,H2|T],[H1|Lf]):-
    H1=H2,
    !,
    elimina_duplicate(T,Lf).
elimina_duplicate([H|T],[H|Lf]):-
    elimina_duplicate(T,Lf).


%interclasare(L1:list, L2:list, LRez:list)
%interclasare(i,i,o)
%L1 - prima lista
%L2 - a doua lista
%LRez - lista rezultata in urma interclasarii celor 2 liste
interclasare([],L,L).
interclasare([H1|T1],L2,Lf):-
    se_afla(H1,L2),
    !,
    interclasare(T1,L2,Lf).
interclasare([H|T],L2,Lf):-
    adauga_pozitie(H, L2, L2f),
    interclasare(T,L2f,Lf).


%ex_a(L1:list, L2:list, LRez:list)
%ex_a(i,i,o)
%L1 - prima lista
%L2 - a doua lista ce poate contine duplicate
%LRez - lista rezultata in urma interclasarii cu eliminarea duplicatelor
ex_a(L1,L2,Lf):-
    elimina_duplicate(L2,L2f),
    interclasare(L1,L2f,Lf).

%interclasare_eterogena(L:list,LCol:list, LRez:list)
%interclasare_eterogena(i,o,o)
%L - lista initiala
%LCol - Lista colectoare
%LRez - Lista rezulata in urma interclasarii
interclasare_eterogena([],LCol,LCol).
interclasare_eterogena([H|T],LCol,LRez):-
    is_list(H),
    %H=[_|_],
    !,
    ex_a(H,LCol,LCol2),
    interclasare_eterogena(T,LCol2,LRez).
interclasare_eterogena([_|T],LCol,LRez):-
    interclasare_eterogena(T,LCol,LRez).
    
%ex_b(L:list, LRez: list)
%ex_b(i,o)
ex_b(L,Rez):-interclasare_eterogena(L,[],Rez).





    



