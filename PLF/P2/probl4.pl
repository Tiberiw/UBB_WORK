se_afla(E,[E|_]):-!.
se_afla(E,[_|T]):-se_afla(E,T).

adauga_pozitie(E,[],[E]):-!.
adauga_pozitie(E,[H|T],[E|[H|T]]):-
    H > E,
    !.
adauga_pozitie(E,[H|T],[H|Rez]):-
    adauga_pozitie(E,T,Rez).

interclasare([],L,L).
interclasare([H1|T1],L2,Lf):-
    se_afla(H1,L2),
    !,
    interclasare(T1,L2,Lf).
interclasare([H|T],L2,Lf):-
    adauga_pozitie(H, L2, L2f),
    interclasare(T,L2f,Lf).

elimina_duplicate([],[]).
elimina_duplicate([H1,H2|T],[H1|Lf]):-
    H1=H2,
    !,
    elimina_duplicate(T,Lf).
elimina_duplicate([H|T],[H|Lf]):-
    elimina_duplicate(T,Lf).

ex_a(L1,L2,Lf):-
    elimina_duplicate(L2,L2f),
    interclasare(L1,L2f,Lf).

interclasare_eterogena([],LCol,LCol).
interclasare_eterogena([H|T],LCol,LRez):-
    is_list(H),
    %H=[_|_],
    !,
    ex_a(H,LCol,LCol2),
    interclasare_eterogena(T,LCol2,LRez).
interclasare_eterogena([_|T],LCol,LRez):-
    interclasare_eterogena(T,LCol,LRez).

ex_b(L,Rez):-interclasare_eterogena(L,[],Rez).





    



