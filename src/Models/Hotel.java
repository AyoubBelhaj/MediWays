    package Models;

    

    public class Hotel {
        private int idHotel;
        private String nomHotel;
        private String adresse;
        private int numtel;
        private static int chambreTotal = 100;

        public Hotel(int idHotel, String nomHotel, String adresse) {
            this.idHotel = idHotel;
            this.nomHotel = nomHotel;
            this.adresse = adresse;
            
        }


        public static int getTotalRooms() {
            return chambreTotal;
        }

        public int getIdHotel() {
            return idHotel;
        }

        public void setIdHotel(int idHotel) {
            this.idHotel = idHotel;
        }

        public String getNomHotel() {
            return nomHotel;
        }

        public void setNomHotel(String nomHotel) {
            this.nomHotel = nomHotel;
        }

        public String getAdresse() {
            return adresse;
        }

        public void setAdresse(String adresse) {
            this.adresse = adresse;
        }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public static void setChambreTotal(int chambreTotal) {
        Hotel.chambreTotal = chambreTotal;
    }
        
        
        
        //public List<Reservation> getReservations() {
        //    return reservations;
        //}

       // public void ajouterReservation(Reservation reservation) throws HotelException {
         //   if (reservation == null) {
         //   throw new HotelException("La réservation est nulle. Impossible d'ajouter.");
        //}
          //  reservations.add(reservation);
        //}

       // public void supprimerReservation(Reservation reservation) {
          //  reservations.remove(reservation);
        //}

       // public double calculerTotalRevenue() {
           // double totalRevenue = 0;
           // for (Reservation reservation : reservations) {
            //    totalRevenue += reservation.getCout();
            //}
           // return totalRevenue;
        //}
        
        //public List<Reservation> getConfirmedReservations() {
        //return reservations.stream()
        //    .filter(Reservation::isConfirmed)
        //    .toList();
        //}

        //public double getTotalRevenue() {
        //    return reservations.stream()
        //        .mapToDouble(Reservation::getCout)
        //        .sum();
        //}

        //public int getAvailableRoomsCount() {
         //   int chambreBook = reservations.size();
         //   return chambreTotal - chambreBook;
        //}

    public int getNumtel() {
        return numtel;
    }

    public static int getChambreTotal() {
        return chambreTotal;
    }
    }
