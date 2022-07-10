# RecruitmentTaskJJD
Zaimplementuj metody findBlockByColor, findBlocksByMaterial, count w klasie Wall. 

Unikaj powielania kodu i umieść całą logikę w klasie Wall.

Uwzględnij w analizie i implementacji interfejs CompositeBlock.

##
interface Structure {

// zwraca dowolny element o podanym kolorze

Optional findBlockByColor(String color);

// zwraca wszystkie elementy z danego materiału

List findBlocksByMaterial(String material);

// zwraca liczbę wszystkich elementów tworzących strukturę

int count();

}
##
public class Wall implements Structure {

private List blocks;

}
##
interface Block {

String getColor();

String getMaterial();

}
##
interface CompositeBlock extends Block {

List getBlocks();

}
