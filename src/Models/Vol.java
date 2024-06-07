package Models;

import java.util.Date;

public record Vol(
        int idVol,
        String airline,
        String flightNumber,
        String departureAirport,
        String destinationAirport,
        Date departureDate,
        Date arrivalDate,
        double ticketPrice
) {
    
    public long calculateFlightDurationInHours() {
        long durationInMilliseconds = arrivalDate.getTime() - departureDate.getTime();
        return durationInMilliseconds / (1000 * 60 * 60); // Milliseconds to hours
    }

    public String getFlightDetails() {
        return "Airline: " + airline + "\nFlight Number: " + flightNumber
                + "\nDeparture Airport: " + departureAirport + "\nDestination Airport: " + destinationAirport
                + "\nDeparture Date: " + departureDate + "\nArrival Date: " + arrivalDate;
    }
}
