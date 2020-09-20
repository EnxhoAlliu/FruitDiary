# FruitDiary
FruitDiary is an Android app where you can memorize fruits you have eaten each day

## External Libraries

### External libraries used are

 1.Retrofit for communicating with API, sending and receiving data
 2.Glide to download and display images
  
## Structure

The architecture used is MVP, where you have the model (Entry, Fruit, EntryFruitDetail), the presenters(EntryPresenter and FruitPresenter) which are responsible 
for sending and fetching data and from and to Views(different Fragments and Adapters). 

Communication of Presenters and Views is done through ServerSync interface.

MainActivity is the starting class which contains 3 Tabs but the number of Tabs can be changed by changing the array elements of "tab_array" in the resources
Each tab has its own fragment but all the operations of editing, deleting and adding are done under "My Diary" tab, in EntriesFragment.

EntriesFragment has receyclerView containing all the entries, and each entry item has also a recyclerView containing all the fruits eaten that day.
You can add an entry by clicking "New Entry button" which will ask you to enter the date.
You can edit an entry by clicking on its date and this will simply open a view inside the fragment where you can can add and edit the fruits and delete this entry.

### After adding a new entry, going back from editing an entry or deleting an entry, the Entries list will be updated


