using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LazyMode : MonoBehaviour
{
	public SpriteRenderer myRend;
    // Start is called before the first frame update
    void Start()
    {
		myRend.enabled = true;
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
