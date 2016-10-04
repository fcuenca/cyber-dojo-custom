using System;
using System.Collections.Generic;

public interface HappyPuppyRepository
{
    IEnumerable<string> RetrieveOwners();
    IEnumerable<string> RetrievePuppies();
    void RemovePuppy(string name);
    void AddOwner(string name);
}

public class ListBasedRepository: HappyPuppyRepository
{
        private Dictionary<string, List<string>> database;

        public ListBasedRepository(Dictionary<string, List<string>> database)
        {
            this.database = database;
        }

        public IEnumerable<string> RetrieveOwners()
        {
            return database["owners"];
        }

        public IEnumerable<string> RetrievePuppies()
        {
            return database["puppies"];
        }

        public void RemovePuppy(string name)
        {
            database["puppies"].Remove(name);
        }

        public void AddOwner(string name)
        {
            database["owners"].Add(name);
        }
}