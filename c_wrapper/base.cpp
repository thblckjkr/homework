#include "base.h"
#include <iostream> // std::cout
#include <stdlib.h> // srand, rand
#include <time.h>   // time

// Impresora
void ImprimeImpresora(char * nombre_archivo){
    std::cout<<"\n Printing " << nombre_archivo << "  \n";
}
int numeroTrabajosEsperaImpresora(){
    srand (time(NULL));
    return rand() % 10 + 1;
}
void LimpiaPrintingQueueImpresora(){
    std::cout<<"\n Queue clean \n";
}

// Plotter
void ImprimePlotter(char * nombre_archivo){
    std::cout<<"\n Printing on plotter" << nombre_archivo << "  \n";
}
int numeroTrabajosEsperaPlotter(){
    srand (time(NULL));
    return rand() % 10 + 1;
}
void LimpiaPrintingQueuePlotter(){
    std::cout<<"\n Plotter queue clean \n";
}

// Caja registradora 
void AbreCajaRegistradora(){
    std::cout<<"\n Cash register login \n";
}
float totalAPagar(){
    srand (time(NULL));
    return rand() % 10 + 1;
}
void EstableceMontoAPagar(float cantidad){
    std::cout<<"\n Added "<< cantidad <<" to payment \n";
}
