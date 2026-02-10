package dev.antoniogrillo.primoesempiocineca.dto.internal;

public record QueryParam<P>(Classi classi, Condizioni condizioni, String parametro,P valore) {
}
