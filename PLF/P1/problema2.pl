% adauga(V: element, L: list, I: index, C: contor, R: result)
% (i,i,i,i,o)
adauga(V,[],_,_,[]) :- !.
adauga(V,[H|T],I,C,[H|Rez]) :- I \== C,
                                I1 is I+1,
                                adauga(V,T,I1,C,Rez).
adauga(V,[H|T],I,C,[H|Rez]) :- I == C,
                                I1 is I+1,
                                C1 is C*2,
                                adauga(V,T,I1,C1,L),
                                Rez = [V|L],
                                !.

% sol(V: element, L: list, LRez: result)
%(i,i,o)
sol2(V,List,LRez) :- adauga(V,List,1,1,LRez).
